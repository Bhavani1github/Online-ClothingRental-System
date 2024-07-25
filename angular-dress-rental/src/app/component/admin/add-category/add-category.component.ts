import { Component } from '@angular/core';
import { take } from 'rxjs';
import { DressRentalService } from 'src/app/dress-rental.service';

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrl: './add-category.component.css'
})
export class AddCategoryComponent {
  categoryName: string = '';
  constructor(
    private service: DressRentalService
  ) {}

  addCategory(): void {
    if (this.categoryName === '') {
      alert("Please add category");
      return;
    }
    const body = {
      categoryName: this.categoryName
    };
    this.service.addCategory(body).pipe(take(1)).subscribe((res) => {
      if (res) {
        alert('Category Added Successfully');
        this.service.navigateToLink('/admin/home');
      }
      console.log('>>>>>');
    });
  }
}
