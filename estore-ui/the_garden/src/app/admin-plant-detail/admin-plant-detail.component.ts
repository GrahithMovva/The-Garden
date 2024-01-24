import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Plant } from '../Plants/Plant';
import { PlantService } from '../Plant.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-admin-plant-detail',
  templateUrl: './admin-plant-detail.component.html',
  styleUrls: ['./admin-plant-detail.component.css']
})
export class AdminPlantDetailComponent implements OnInit {

  Plant!: Plant;
  username !: String;


  constructor(
    private route: ActivatedRoute,
    private PlantService: PlantService,
    private location: Location,
    
  ) {}

  ngOnInit(): void {
    this.getPlant();
    
  }

  getPlant(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.PlantService.getPlant(id)
      .subscribe(Plant => this.Plant = Plant);
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    if (this.Plant) {
      this.PlantService.updatePlant(this.Plant)
        .subscribe(() => this.goBack());
    }
  }

}
