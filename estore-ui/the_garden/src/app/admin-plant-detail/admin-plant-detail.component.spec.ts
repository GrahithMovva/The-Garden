import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminPlantDetailComponent } from './admin-plant-detail.component';

describe('AdminPlantDetailComponent', () => {
  let component: AdminPlantDetailComponent;
  let fixture: ComponentFixture<AdminPlantDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminPlantDetailComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminPlantDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
