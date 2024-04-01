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
    this.sortedQuantL = this.needService.sortQuant();
    this.sortedQuantH = this.needService.sortQuantHigh();
    this.sortedCostL = this.needService.sortCostLow();
    this.sortedCostH = this.needService.sortCostHigh();
  }

  getNeeds(): void {
    this.needService.getNeeds()
      .subscribe(needs => this.needs = needs.slice(0, 5));
  }

  toggleA(): void {
    // Toggle the value of showA when the button is clicked
    this.showA = !this.showA;
  }

  toggleQuant(): void {
    // Toggle the value of showA when the button is clicked
    this.showQuantL = !this.showQuantL;
  }

  toggleQuantH(): void {
    // Toggle the value of showA when the button is clicked
    this.showQuantH = !this.showQuantH;
  }

  toggleCostL(): void {
    // Toggle the value of showA when the button is clicked
    this.showCostL = !this.showCostL;
  }

  toggleCostH(): void {
    // Toggle the value of showA when the button is clicked
    this.showCostH = !this.showCostH;
  }


}