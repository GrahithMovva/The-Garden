import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';



import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { PlantDetailComponent } from './Plant-detail/Plant-detail.component';
import { PlantsComponent } from './Plants/Plants.component';
import { PlantSearchComponent } from './Plant-search/Plant-search.component';
import { MessagesComponent } from './messages/messages.component';
import { LoginComponent } from './login/login.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { AdminComponentComponent } from './admin-component/admin-component.component';
import { AdminPlantComponent } from './admin-plant/admin-plant.component';
import { AdminPlantDetailComponent } from './admin-plant-detail/admin-plant-detail.component';
import { UserRequestComponent } from './user-request/user-request.component';
import { AdminRequestComponent } from './admin-request/admin-request.component';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  declarations: [
    PlantsComponent,
    AppComponent,
    DashboardComponent,
    PlantSearchComponent,
    PlantDetailComponent,
    MessagesComponent,
    PlantSearchComponent,
    LoginComponent,
    SignUpComponent,
    ShoppingCartComponent,
    AdminComponentComponent,
    AdminPlantComponent,
    AdminPlantDetailComponent,
    UserRequestComponent,
    AdminRequestComponent
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }