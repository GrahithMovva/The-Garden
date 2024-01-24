import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import {FormGroup, FormBuilder } from "@angular/forms";
import { Router } from '@angular/router';
import { UserService } from '../User.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  public signupForm !: FormGroup;
  constructor(private formBuilder: FormBuilder, private userservice : UserService, private router : Router ) { }

  ngOnInit(): void {
    this.signupForm = this.formBuilder.group({
      username:[''],
      password:['']
    })
  }
  signup(){
    this.userservice.addUser(this.signupForm.value)
    .subscribe(res=>{
      alert("signup Successfull");
      this.signupForm.reset();
      this.router.navigate(['login'])
    },err=>{
      alert("Something went wrong")
    })
  }

}
