import { Component } from '@angular/core';
import { User } from './User';
import { Plant } from './Plants/Plant';



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Plant Store';
  static user: User

  static setUser(user: User){
    this.user = user;
  }

  static addtocart(plant: Plant){
    this.user.cart.push(plant)
  }
}
