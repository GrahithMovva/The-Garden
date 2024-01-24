import { Component, OnInit } from '@angular/core';

import { Observable, Subject } from 'rxjs';

import {
   debounceTime, distinctUntilChanged, switchMap
 } from 'rxjs/operators';

import { Plant } from '../Plants/Plant';
import { PlantService } from '../Plant.service';

@Component({
  selector: 'app-Plant-search',
  templateUrl: './Plant-search.component.html',
  styleUrls: [ './Plant-search.component.css' ]
})
export class PlantSearchComponent implements OnInit {
  Plants$!: Observable<Plant[]>;
  

  private searchTerms = new Subject<string>();

  constructor(private plantService: PlantService) {}

  // Push a search term into the observable stream.
  search(term: string): void {
    this.searchTerms.next(term);
  }

  ngOnInit(): void {
    this.Plants$ = this.searchTerms.pipe(
      // wait 300ms after each keystroke before considering the term
      debounceTime(300),

      // ignore new term if same as previous term
      distinctUntilChanged(),

      // switch to new search observable each time the term changes
      switchMap((term: string) => this.plantService.searchPlants(term)),
    );
  }
}