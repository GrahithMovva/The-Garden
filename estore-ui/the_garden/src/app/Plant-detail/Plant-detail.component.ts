import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Plant } from '../Plants/Plant';
import { PlantService } from '../Plant.service';
import { ShoppingCartService } from '../shopping-cart.service';
import { User } from '../User';
import { UserService } from '../User.service';


@Component({
  selector: 'app-Plant-detail',
  templateUrl: './Plant-detail.component.html',
  styleUrls: [ './Plant-detail.component.css' ]
})
export class PlantDetailComponent implements OnInit {
  Plant!: Plant;
  username !: String;
  user !: User
  


  constructor(
    private route: ActivatedRoute,
    private PlantService: PlantService,
    private location: Location,
    private ShoppingCartService:ShoppingCartService,
    private userService : UserService
    
  ) {}

  ngOnInit(): void {
    this.getPlant();
    this.userService.getCurrentUser().subscribe(User => this.username = User.username)
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

  addtoShoppingCart(){
    
    this.ShoppingCartService.addToCart(this.Plant)
    .subscribe(); 
    this.userService.getCurrentUser().subscribe(User =>this.user = User)
    this.userService.updateUser(this.user).subscribe();
  }
}