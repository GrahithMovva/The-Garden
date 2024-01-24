import { Component, OnInit } from '@angular/core';
import { Plant } from '../Plants/Plant';
import { UserRequestService } from '../UserRequest.service';

@Component({
  selector: 'app-user-request',
  templateUrl: './user-request.component.html',
  styleUrls: ['./user-request.component.css']
})
export class UserRequestComponent implements OnInit {

  constructor(private requestservice: UserRequestService) { }

  ngOnInit(): void {
  }

  add(name: string): void {
    name = name.trim();
    if (!name) { return; }
    this.requestservice.addPlant({ name } as Plant)
      .subscribe();
  }
}
