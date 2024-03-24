import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Need } from '../need';
import { User } from '../user';
import { FundingBasket } from '../fundingbasket';
import { NeedService } from '../need.service';

@Component({
  selector: 'app-user-detail',
  templateUrl: './userdetail.component.html',
  styleUrls: [ './userdetail.component.css' ]
})
export class UserDetailComponent implements OnInit {
  need: Need | undefined;
  fundingbasket: FundingBasket | undefined;
  user: User[] = [];

  constructor(
    private route: ActivatedRoute,
    private needService: NeedService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.getNeed();
    //this.getFundingBasket();
  }

  addtoBasket(id: number, name: string, cost: number, quantity: number, type: string): void {
    name = name.trim();
    if (!name) { return; }
    //this.
    this.needService.addBasket({id, name, cost, quantity, type } as FundingBasket)
      .subscribe(basket => {
        console.log(`${name} has been added to the basket.`);
      });
  }
  
  getNeed(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.needService.getNeed(id)
      .subscribe(need => this.need = need);
  }

  getUser(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.needService.getUser()
      .subscribe(user => this.user = user);
  }

  getFundingBasket(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.needService.getFundingBasket(id)
      .subscribe(fundingbasket => this.fundingbasket = fundingbasket);
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    if (this.need) {
      this.needService.updateNeed(this.need)
        .subscribe(() => this.goBack());
    }
  }
}