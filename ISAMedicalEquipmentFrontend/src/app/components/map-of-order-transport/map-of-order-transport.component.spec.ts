import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MapOfOrderTransportComponent } from './map-of-order-transport.component';

describe('MapOfOrderTransportComponent', () => {
  let component: MapOfOrderTransportComponent;
  let fixture: ComponentFixture<MapOfOrderTransportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MapOfOrderTransportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MapOfOrderTransportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
