import { Component } from '@angular/core';
import { Product } from '../../model/product.model';
import { DressRentalService } from 'src/app/dress-rental.service';
import { Router } from '@angular/router';
import { take } from 'rxjs';

@Component({
  selector: 'app-admin-product-list',
  templateUrl: './admin-product-list.component.html',
  styleUrls:['./admin-product-list.component.css']
})
export class AdminProductListComponent {
  productList: Array<Product> = [];
  getCategoryList: any[] = [];
  category: any = 100;
  allProductList: Array<Product> = [];
  offset: number = 0;
  pageSize: number = 10; // How many item you want to display in your page.
  totalclothes: any = 1;

  constructor(
    private bservice: DressRentalService,
    private router: Router
  ) {
    this.bservice.isUserLoginPresent();
    this.getProductList(true);
  }

  ngOnInit(): void {
   
  }

  // getProductList(isAllclothes: boolean = false): void {
  //   let book: any = this.bservice.getAllClothes(this.offset - 1 < 0 ? 0 : this.offset - 1, this.pageSize);
  //   this.bservice.getAllProducts().pipe(take(1)).subscribe((res: any) => {
  //     if (res  && Array.isArray(res)) {
  //       this.productList = res;
  //       this.allProductList = res;
  //       this.totalclothes = res;
  //     }
  //   }, (err: any) => {
  //     console.log("Error");
  //   });

  // }

  getProductList(isAllBook: boolean = false): void {
      let product: any = this.bservice.getProductByPage(this.offset - 1 < 0 ? 0 : this.offset - 1, this.pageSize);
      /*if (!isAllBook) {
        book = this.bservice.getBookByCategory(this.category, this.offset - 1 < 0 ? 0 : this.offset - 1, this.pageSize);
       console.log("++++++",isAllBook);
      }*/
     product.pipe(take(1)).subscribe((res: any) => {
      console.log('>>>>>>>>>', res)
        if (res && res?.product && Array.isArray(res?.product)) {
          this.productList = res?.product;
          this.allProductList = res?.product;
          this.totalclothes = res?.totalProduct;
        }
      }, (err: any) => {
        console.log("Error");
      });
  }

  delProduct(product: Product): void {
    this.bservice.deleteProduct(product?.pid).pipe(take(1)).subscribe(
      (res: any) => {
        alert("Product deleted sucessfully");
        this.getProductList(this.category === 100 || this.category === "100");
      }, err => {
        console.log("Error");
      }
    )
  }

  editProduct(product: Product): void {
    this.router.navigate(['/admin/add-product'], {
      queryParams: {
        id: product?.pid
      }
    });

  }

  getclothesByCategory(): void {
    this.offset = 0;
    this.totalclothes = 1;
    if (this.category === "100") {
      this.getProductList(true);
    } else {
      this.getProductList(false);
    }
  }

  onNextPageClick(pageOffSet: any): void {
    this.offset = pageOffSet;
    this.getProductList(this.category === 100 || this.category === "100");
  }

  onPreviousPageClick(pageOffSet: any): void {
    this.offset -= 1;
    this.getProductList(this.category === 100 || this.category === "100");
  }

  onFirstPageClick(pageOffSet: any): void {
    this.offset = 0;
    this.getProductList(this.category === 100 || this.category === "100");
  }

  onLastPageClick(pageOffSet: any): void {
    const lastPage = Math.ceil(this.totalclothes / this.pageSize);
    this.offset = lastPage;
    this.getProductList(this.category === 100 || this.category === "100");
  }
}
