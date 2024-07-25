import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  slides = [
    { image: '../../../assets/Images/bghome1.jpg', caption: 'Caption 1' },
    { image: '../../../assets/Images/homebg2.jpg', caption: 'Caption 2' },
    { image: '../../../assets/Images/homebg3.jpg', caption: 'Caption 3' }
  ];
  currentSlide = 0;
  slideInterval: any;

  constructor(private route: Router) {}

  ngOnInit(): void {
    this.showSlides();
  }

  showSlides() {
    this.slideInterval = setInterval(() => {
      this.nextSlide();
    }, 5000); // Change image every 5 seconds
  }

  nextSlide() {
    this.currentSlide = (this.currentSlide + 1) % this.slides.length;
  }

  previousSlide() {
    this.currentSlide = (this.currentSlide - 1 + this.slides.length) % this.slides.length;
  }

  gotoLogin(): void {
    this.route.navigate(['/user-login']);
  }

  ngOnDestroy(): void {
    clearInterval(this.slideInterval);
  }
}