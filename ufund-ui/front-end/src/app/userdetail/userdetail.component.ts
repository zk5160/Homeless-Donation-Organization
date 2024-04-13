import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Need } from '../need';
import { User } from '../user';
import { FundingBasket } from '../fundingbasket';
import { NeedService } from '../need.service';
import { CurrentUserService } from '../current-user.service';

@Component({
  selector: 'app-user-detail',
  templateUrl: './userdetail.component.html',
  styleUrls: ['./userdetail.component.css'],
})
export class UserDetailComponent implements OnInit {
  need: Need | undefined;
  fundingbasket: FundingBasket | undefined;
  user: User[] = [];

  constructor(
    private route: ActivatedRoute,
    private needService: NeedService,
    private currentuserservice: CurrentUserService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.getNeed();
    //this.getFundingBasket();
  }

  addtoBasket(
    id: number,
    name: string,
    cost: number,
    quantity: number,
    type: string
  ): void {
    name = name.trim();
    if (!name) {
      return;
    }
    //this.
    this.needService
      .addBasket({ id, name, cost, quantity, type } as FundingBasket)
      .subscribe((basket) => {
        console.log(`${name} has been added to the basket.`);
      });
  }

  add(item: Need): void {
    this.currentuserservice.addToCart(item);
    // Check if need is defined and has a quantity property
    if (this.need && this.need.quantity !== undefined) {
      if (this.need.quantity != 0) {
        this.need.quantity--;
      }
      if (this.need.quantity == 0) {
        document.getElementById('text-container')!.classList.add('zero');
      }
    }
  }

  getNeed(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.needService.getNeed(id).subscribe((need) => (this.need = need));
  }

  getUser(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.needService.getUser().subscribe((user) => (this.user = user));
  }

  getFundingBasket(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.needService
      .getFundingBasket(id)
      .subscribe((fundingbasket) => (this.fundingbasket = fundingbasket));
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    if (this.need) {
      this.needService.updateNeed(this.need).subscribe(() => this.goBack());
    }
  }
}
