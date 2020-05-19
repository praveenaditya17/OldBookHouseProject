import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeliveryBuyRequestComponent } from './delivery-buy-request.component';

describe('DeliveryBuyRequestComponent', () => {
  let component: DeliveryBuyRequestComponent;
  let fixture: ComponentFixture<DeliveryBuyRequestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeliveryBuyRequestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeliveryBuyRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
