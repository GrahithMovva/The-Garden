import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminPlantComponent } from './admin-plant.component';

describe('AdminPlantComponent', () => {
  let component: AdminPlantComponent;
  let fixture: ComponentFixture<AdminPlantComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminPlantComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminPlantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
