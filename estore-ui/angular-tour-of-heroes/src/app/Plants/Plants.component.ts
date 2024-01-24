import { Component, OnInit } from '@angular/core';

import { Plant } from './Plant';
import { PlantService } from '../Plant.service';
import { debounceTime, distinctUntilChanged, Observable, Subject, switchMap } from 'rxjs';

@Component({
  selector: 'app-Plants',
  templateUrl: './Plants.component.html',
  styleUrls: ['./Plants.component.css']
})
export class PlantsComponent implements OnInit {
  Plants: Plant[] = [];
  plants$!: Observable<Plant[]>;
  private searchTerms = new Subject<string>();
  constructor(private PlantService: PlantService) { }

  ngOnInit(): void {
    this.getPlants();
    this.plants$ = this.searchTerms.pipe(
      // wait 300ms after each keystroke before considering the term
      debounceTime(300),

      // ignore new term if same as previous term
      distinctUntilChanged(),

      // switch to new search observable each time the term changes
      switchMap((term: string) => this.PlantService.searchPlants(term)),
    );
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
  search(term: string): void {
    this.searchTerms.next(term);
  }
}