import { Component, OnInit } from '@angular/core';
import { DressRentalService } from 'src/app/dress-rental.service';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent implements OnInit{

  userName: string = '';
  constructor(
    private bservice: DressRentalService
  ) {
    if (this.bservice.getUserName() !== null) {
      this.userName = this.bservice.getUserName();
      console.log("*******",this.userName);
    }
    this.bservice.isUserLoginPresent();
  }

  ngOnInit(): void {
  }
}
