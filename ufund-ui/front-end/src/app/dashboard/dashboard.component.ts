import { Component, OnInit } from '@angular/core';
import { Need } from '../need';
import { NeedService } from '../need.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: [ './dashboard.component.css' ]
})
export class DashboardComponent implements OnInit {
  needs: Need[] = [];
  sortedList: Observable<Need[]> | undefined; 
  sortedQuantL: Observable<Need[]> | undefined; 
  sortedQuantH: Observable<Need[]> | undefined; 
  sortedCostL: Observable<Need[]> | undefined; 
  sortedCostH: Observable<Need[]> | undefined; 
  showA: boolean = false;
  showQuantL : boolean = false;
  showQuantH : boolean = false;
  showCostL : boolean = false;
  showCostH : boolean = false;

  constructor(private needService: NeedService) { }

  ngOnInit(): void {
    this.getNeeds();
    this.sortedList = this.needService.sortA();
    this.sortedQuantL = this.needService.sortQuantLow();
    this.sortedQuantH = this.needService.sortQuantHigh();
    this.sortedCostL = this.needService.sortCostLow();
    this.sortedCostH = this.needService.sortCostHigh();
  }

  getNeeds(): void {
    this.needService.getNeeds()
      .subscribe(needs => this.needs = needs.slice());
  }

  toggleA(): void {
    // Toggle the value of showA when the button is clicked
    this.showA = !this.showA;
    this.showQuantL = false;
    this.showQuantH = false;
    this.showCostL = false;
    this.showCostH = false;

  }

  toggleQuantL(): void {
    // Toggle the value of showA when the button is clicked
    this.showQuantL = !this.showQuantL;
    this.showA = false;
    this.showQuantH = false;
    this.showCostL = false;
    this.showCostH = false;
  }

  toggleQuantH(): void {
    // Toggle the value of showA when the button is clicked
    this.showQuantH = !this.showQuantH;
    this.showA = false;
    this.showQuantL = false;
    this.showCostL = false;
    this.showCostH = false;
  }

  toggleCostL(): void {
    // Toggle the value of showA when the button is clicked
    this.showCostL = !this.showCostL;
    this.showA = false;
    this.showQuantL = false;
    this.showQuantH = false;
    this.showCostH = false;
  }

  toggleCostH(): void {
    // Toggle the value of showA when the button is clicked
    this.showCostH = !this.showCostH;
    this.showA = false;
    this.showQuantL = false;
    this.showQuantH = false;
    this.showCostL = false;
  }


}