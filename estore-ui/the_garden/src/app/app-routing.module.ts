import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { PlantsComponent } from './Plants/Plants.component';
import { PlantDetailComponent } from './Plant-detail/Plant-detail.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { AdminPlantComponent } from './admin-plant/admin-plant.component';
import { AdminPlantDetailComponent } from './admin-plant-detail/admin-plant-detail.component';
import { UserRequestComponent } from './user-request/user-request.component';
import { AdminRequestComponent } from './admin-request/admin-request.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'detail/:id', component: PlantDetailComponent },
  { path: 'Plants', component: PlantsComponent },
  { path: 'login', component: LoginComponent}, 
  { path: 'sign-up', component: SignUpComponent } ,
  {path: 'shopping-cart', component: ShoppingCartComponent},
  {path: 'admin-plant-detail/:id', component: AdminPlantDetailComponent},
  {path: 'admin-plant', component: AdminPlantComponent},
  {path: 'user-request' , component: UserRequestComponent},
  {path: 'admin-request', component: AdminRequestComponent}

];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}