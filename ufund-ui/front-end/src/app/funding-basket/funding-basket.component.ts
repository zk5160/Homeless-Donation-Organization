import { Component, OnInit } from '@angular/core';
//import * as fs from 'fs'; // Import the file system module

//import { Need } from '../need';
import { FundingBasket } from '../fundingbasket';
import { User } from '../user';
import { NeedService } from '../need.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-funding-basket',
  templateUrl: './funding-basket.component.html',
  styleUrls: ['./funding-basket.component.css']
})
export class FundingBasketComponent implements OnInit {
  //needs: Need[] = [];
  fundingbasket: FundingBasket[] = [];
  user: User[] = [];


  constructor(private needService: NeedService,
    private location: Location) { }

  ngOnInit(): void {
    //this.getNeeds();
    this.getFundingBasket();
    this.getUser();
  }

  // getNeeds(): void {
  //   this.needService.getNeeds()
  //   .subscribe(needs => this.needs = needs);
  // }

  getFundingBasket(): void {
    // Call the method to fetch funding baskets from the service
    this.needService.getFundingBaskets()
      .subscribe(fundingbasket => this.fundingbasket = fundingbasket);
  }

  getUser(): void {
    // Call the method to fetch user from the service
    this.needService.getUser()
      .subscribe(user => this.user = user);
  }

  goBack(): void {
    this.location.back();
  }
  save(): void {
    if (this.fundingbasket && this.fundingbasket.length > 0) {
      for (const basket of this.fundingbasket) {
        this.needService.updateBasket(basket)
          .subscribe(
            () => {
              // Basket updated successfully, you can perform any additional logic here if needed
              console.log(`Basket with ID ${basket.id} updated successfully.`);
            },
            (error) => {
              // Handle error
              console.error(`Error updating basket with ID ${basket.id}:`, error);
            }
          );
      }
      //this.copyBasket();
    } else {
      console.warn("No baskets to update.");
    }
  }
  
  // save(): void {
  //   if (this.fundingbasket) {
  //     this.needService.updateBasket(this.fundingbasket)
  //       .subscribe(() => this.goBack());
  //   }
  // }

  delete(basket: FundingBasket): void {
    this.fundingbasket = this.fundingbasket.filter(h => h !== basket);
    this.needService.deleteBasket(basket.id).subscribe();
  }

}