import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NeedsComponent } from './needs.component';

describe('HeroesComponent', () => {
  let component: NeedsComponent;
  let fixture: ComponentFixture<NeedsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NeedsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(NeedsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
