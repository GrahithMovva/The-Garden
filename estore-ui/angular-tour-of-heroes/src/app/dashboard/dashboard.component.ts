import { Component, OnInit } from '@angular/core';
import { Plant } from '../Plants/Plant';
import { PlantService } from '../Plant.service';
import { User } from '../User';
import { AppComponent } from '../app.component';
import { UserService } from '../User.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: [ './dashboard.component.css' ]
})
export class DashboardComponent implements OnInit {
  Plants: Plant[] = [];
  allPlants: Plant[] = [];
  user !: User;
  SelectedPrice!: number;
  
  constructor(private PlantService: PlantService, private userService : UserService) { }

  ngOnInit(): void {
    this.getPlants();
    this.getAllPlants();
    this.SelectedPrice = 0;
    ;
  }

  getPlants(): void {
    this.PlantService.getPlants()
      .subscribe(Plants => this.Plants = Plants.slice(1, 5));
  }

  getAllPlants(): void {
    this.PlantService.getPlants()
      .subscribe(Plants => this.allPlants = Plants);
  }

  filterPlantsLowToHigh(): void {
    this.allPlants.sort((a,b) => a.price < b.price?-1:1);
  }

  filterPlantsHighToLow(): void {
    this.allPlants.sort((a,b) => a.price > b.price?-1:1);
  }
}