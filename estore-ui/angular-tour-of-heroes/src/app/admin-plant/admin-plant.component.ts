import { Component, OnInit } from '@angular/core';
import { Plant } from '../Plants/Plant';
import { PlantService } from '../Plant.service';

@Component({
  selector: 'app-admin-plant',
  templateUrl: './admin-plant.component.html',
  styleUrls: ['./admin-plant.component.css']
})
export class AdminPlantComponent implements OnInit {
  Plants: Plant[] = [];

  constructor(private PlantService: PlantService) { }

  ngOnInit(): void {
    this.getPlants();
  }

  getPlants(): void {
    // this.PlantService.getPlants()
    // .subscribe(Plants => this.Plant = Plants);
    this.PlantService.getPlants().subscribe(Plants => this.Plants = Plants);
  }

  add(name: string): void {
    name = name.trim();
    if (!name) { return; }
    this.PlantService.addPlant({ name } as Plant)
      .subscribe(Plant => {
        this.Plants.push(Plant);
      });
  }

  delete(Plant: Plant): void {
    // this.Plant = this.Plant.filter(h => h !== Plant);
    // this.PlantService.deletePlant(Plant.id).subscribe();
    this.Plants = this.Plants.filter(h => h !== Plant);
    this.PlantService.deletePlant(Plant.id).subscribe();
  }


}
