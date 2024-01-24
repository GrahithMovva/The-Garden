import { Component, Input, OnInit } from '@angular/core';
import { Plant } from '../Plants/Plant';
import { ShoppingCartService } from '../shopping-cart.service';
import { User } from '../User';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { UserService } from '../User.service';
import { ThisReceiver } from '@angular/compiler';




@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

  shoppingCart !: Plant[];
  user !: User;
 

  constructor(
    private userService : UserService,
    private shoppingCartService: ShoppingCartService,
    private location: Location,
    private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    this.getPlants()
    
    
  }
  getPlants(): void {
    // this.PlantService.getPlants()
    // .subscribe(Plants => this.Plant = Plants);
    
    
    this.shoppingCartService.getCart().subscribe(Plants => this.shoppingCart = Plants);
    

    
  }


  checkout(): void {
    
    this.shoppingCart.forEach(plant => this.removeFromCart(plant))
    
    
    
  }

  removeFromCart(plant : Plant): void{
   
    this.shoppingCartService.removeFromCart(plant).subscribe(Plants => this.shoppingCart = Plants);
  }



  BackButton(): void {
    this.location.back();
  }

}
