import { Component, OnInit } from '@angular/core';
import { Plant } from '../Plants/Plant';
import { UserRequestService } from '../UserRequest.service';
import { PlantService } from '../Plant.service';

@Component({
  selector: 'app-admin-request',
  templateUrl: './admin-request.component.html',
  styleUrls: ['./admin-request.component.css']
})
export class AdminRequestComponent implements OnInit {
  Plants: Plant[] = [];
  constructor(private requestService : UserRequestService, private PlantService: PlantService) { }

  ngOnInit(): void {
    this.getPlants()
  }

  getPlants(): void {
    // this.PlantService.getPlants()
    // .subscribe(Plants => this.Plant = Plants);
    this.requestService.getPlants().subscribe(Plants => this.Plants = Plants);
  }

  add(plant: Plant): void {
    this.PlantService.addPlant(plant).subscribe();
    this.delete(plant);
    
  }

  delete(Plant: Plant): void {
    // this.Plant = this.Plant.filter(h => h !== Plant);
    // this.PlantService.deletePlant(Plant.id).subscribe();
    this.Plants = this.Plants.filter(h => h !== Plant);
    this.requestService.deletePlant(Plant.id).subscribe();
  }
}
