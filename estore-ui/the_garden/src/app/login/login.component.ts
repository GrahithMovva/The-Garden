import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup} from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../User.service';
import { User } from '../User';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public loginForm !: FormGroup
  user !: User
  constructor(private formbuilder : FormBuilder, private userservice : UserService, private router : Router) { }

  ngOnInit(): void {
    this.loginForm = this.formbuilder.group({
      username: [''],
      password:['']
    })
  }
  login(){
    this.userservice.getUsers()
    .subscribe(res=>{
      const user = res.find((a:any)=>{
        return a.username === this.loginForm.value.username && a.password === this.loginForm.value.password
      });
      if(user){
        if(user.id == 0){
          this.loginForm.reset();
          this.user = user;
          this.router.navigate(['admin-plant'])
          this.userservice.setCurrentUser(user).subscribe();
        }
        else{
          this.loginForm.reset();
          this.user = user;
          this.userservice.setCurrentUser(user).subscribe();
          this.router.navigate(['dashboard'])
        }
      }
      else{
        alert("user not found")
      }
    },err=>{
      alert('something went wrong')
    })
    
  }
}
