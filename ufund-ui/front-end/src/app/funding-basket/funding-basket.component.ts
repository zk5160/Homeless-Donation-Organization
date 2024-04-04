import { Component, OnInit } from '@angular/core';
//import * as fs from 'fs'; // Import the file system module

//import { Need } from '../need';
import { FundingBasket } from '../fundingbasket';
import { User } from '../user';
import { Need } from '../need';
import { NeedService } from '../need.service';
import { CurrentUserService } from '../current-user.service';
import { UserService } from '../user.service';
import { Location } from '@angular/common';
import { tap, catchError } from 'rxjs/operators';
import { of } from 'rxjs';


@Component({
  selector: 'app-funding-basket',
  templateUrl: './funding-basket.component.html',
  styleUrls: ['./funding-basket.component.css'],
})
export class FundingBasketComponent implements OnInit {
  //needs: Need[] = [];
  fundingbasket: FundingBasket[] = [];
  user: User[] = [];
  EmptyBasket : boolean = false;

  constructor(
    private needService: NeedService,
    private currentuserservice: CurrentUserService,
    private userService: UserService,
    private location: Location
  ) {}

  ngOnInit(): void {
    //this.getNeeds();
    //this.getFundingBasket();
    //this.getUser();

    this.userService
      .searchUsers(this.currentuserservice.getCurrentUser())
      .subscribe((currentcart: User[]) => {
        this.fundingbasket = currentcart[0].basket;
      });

    this.fundingbasket = this.currentuserservice.getCurrentUserCart();

    if (this.fundingbasket.length == 0 ){
      alert("NOTHING IN BASKET");
    }
    this.nothing()
  }

  nothing(): void {
    if (this.fundingbasket.length ==0){
      this.EmptyBasket = true;
    }
    else {
      this.EmptyBasket = false;
    }
  }

  getFundingBasket(): void {
    // Call the method to fetch funding baskets from the service
    this.needService
      .getFundingBaskets()
      .subscribe((fundingbasket) => (this.fundingbasket = fundingbasket));
  }

  getUser(): void {
    // Call the method to fetch user from the service
    this.needService.getUser().subscribe((user) => (this.user = user));
  }

  goBack(): void {
    this.location.back();
  }
  save(): void {
    if (this.fundingbasket && this.fundingbasket.length > 0) {
      for (const basket of this.fundingbasket) {
        this.needService.updateBasket(basket).pipe(
          tap(() => {
            // Basket updated successfully, you can perform any additional logic here if needed
            console.log(`Basket with ID ${basket.id} updated successfully.`);
          }),
          catchError(error => {
            // Handle error
            console.error(`Error updating basket with ID ${basket.id}:`, error);
            return of(null); // Returning an observable here to swallow the error
          })
        ).subscribe();
      }
      //this.copyBasket();
    } else {
      console.warn('No baskets to update.');
    }
  }
  

  // save(): void {
  //   if (this.fundingbasket) {
  //     this.needService.updateBasket(this.fundingbasket)
  //       .subscribe(() => this.goBack());
  //   }
  // }

  delete(item: Need): void {
    // this.fundingbasket = this.fundingbasket.filter(h => h !== basket);
    // this.needService.deleteBasket(basket.id).subscribe();
    this.currentuserservice.removeOneFromCart(item);
    this.updateFundingBasket();
  }

  add(item: Need): void {
    this.currentuserservice.addToCart(item);
    this.updateFundingBasket();
  }

  removeAll(item: Need): void {
    this.currentuserservice.removeAllFromCart(item);
    this.updateFundingBasket();
  }

  checkout(): void {
    if (this.fundingbasket.length == 0) {
      alert("CART IS EMPTY MY GUY WHAT'RE YOU DOING LOCK IN");
    } else {
      this.currentuserservice.clearCart();
    }
    this.currentuserservice.checkout();
  }

  updateFundingBasket(): void {
    this.fundingbasket = this.currentuserservice.getCurrentUserCart();
  }
}
