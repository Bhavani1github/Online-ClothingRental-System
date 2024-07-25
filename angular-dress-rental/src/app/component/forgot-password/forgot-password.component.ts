import { Component } from '@angular/core';
import { EmailValidator } from '@angular/forms';
import { Router } from '@angular/router';
import { take } from 'rxjs';
import { DressRentalService } from 'src/app/dress-rental.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent {
  emailId: string= '';
  isShowChangePassword: boolean = false;
  newPassword: string = '';
  user: any;

  constructor(
    private bservice: DressRentalService,
    private route: Router
  ) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
   
    this.bservice.findUserByEmail(this.emailId).pipe(take(1)).subscribe((res) => {
      if (!!res && res?.id) {
        this.user = res;
        this.isShowChangePassword = true;
      }
    }, err => {
      this.isShowChangePassword = false;
      alert("User not found . Please enter valid email.")
    });
  }

  onChangePassword(): void {
    // this.user.passw = this.newPassword;
    this.bservice.changeUserPassword(this.user?.id, this.newPassword).pipe(take(1)).subscribe((res) => {
      if (res && res.id) {
        alert("Password changed successfully");
        this.route.navigate(["/user-login"]);
      }
    }, error => {
      alert("Error occured while changing password.");
    });
  }
}
