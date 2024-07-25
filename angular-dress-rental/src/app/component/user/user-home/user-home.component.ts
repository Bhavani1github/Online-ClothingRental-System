import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subject, debounceTime, distinctUntilChanged, take } from 'rxjs';
import { DressRentalService } from 'src/app/dress-rental.service';
import { Product } from '../../model/product.model';

@Component({
  selector: 'app-user-home',
  templateUrl: './user-home.component.html',
  styleUrls: ['./user-home.component.css']
})

export class UserHomeComponent implements OnInit {
  productList: Array<Product> = [];
  quantity: number = 0;
  user: any = {};
  getCategoryList: any[] = [];
  category: any = 100;
  allProductList : Array<Product>= [];
  offset: number = 0;
  pageSize: number = 5; // How many item you want to display in your page.
  totalclothes: number = 1;
  searchType: string = "bycategory";
  searchKeyword: string = "";
  userInputUpdate = new Subject<string>();
  size: string = "";

  constructor(
    private bservice: DressRentalService,
    private router: Router,
    private snakcbar: MatSnackBar
  ) {
   this.bservice.isUserLoginPresent();
    this.getProductList(true);
    this.getUserDetail();

  }


  ngOnInit(): void {
    this.bservice.getAllCategoryList().pipe(take(1)).subscribe((res: any) => {
      if (res) {
        console.log('>>>>>>>>', res);
        this.getCategoryList = res;
      }
    });
    this.userInputUpdate.pipe(
      debounceTime(400),
      distinctUntilChanged())
      .subscribe(value => {
        if (value.length > 0) {

          this.bservice.getProductSearch(value).pipe((take(1))).subscribe((res: any) => {
            console.log('$$$$$$$$', res);
            if (res) {
                this.productList = res;
                this.allProductList = res;
            }
          });
        } else {
          this.productList = [];
        }
        
      });
  }

  getUserDetail(): void {
    const cid = this.bservice.getUserAuthorization();
    this.bservice.getUserById(cid).pipe(take(1)).subscribe(
      (res: any) => {
        console.log("User***", res);
        if (!!res && res?.id) {
          this.user = res;
        }
      }, err => {
        console.log("Err");
      }
    )
  }


  getProductList(isAllclothes: boolean = false): void {
    this.bservice.getAllProducts().pipe(take(1)).subscribe((res: any) => {
      if (res && Array.isArray(res)) {
        this.productList = res;
        this.allProductList = res;
        console.log('^^^^^', this.productList)
      }
    }, (err: any) => {
      console.log("Error");
    });
  }

  addToCart(product: Product): void {
  const element: any = document.getElementById(product?.pid.toString());
  let qty:any= element!==null ? element.value : 0; 
  if(qty ===""){
    element.value=0;
    qty=0;
  }
    if (qty === 0 || qty === "0" || qty <0) {
      alert("Qunatity must be more than zero");
      return ;
    }

    if (qty > product?.pQuantity) {
      alert('Added quantity should not greater than available quantity');
      return;
    }
    
    const body: any = {
      quantity: qty,
      mrpPrice: product?.mrpPrice,
      user: this.user,
      product: product,
      size:product?.size,
      duration:product?.duration
    };
    console.log("add to cart:: ", body);
    this.bservice.addToCart(body, product?.pid, this.user?.id).pipe(take(1)).subscribe(
      (res: any) => {
        console.log(res);
        if (!!res && res?.cartId) {
        alert("Added to cart sucessfully");
          this.getProductList(true);
          this.bservice.navigateToLink('/user/cart');
        }
      }, err => {
        console.log("Error");
      }
      
    )
    
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
  gotocartList(): void {
    this.router.navigate(["/user/cart"]);
  }
  
  getSelectedType(event: any): void {
    this.searchType = event?.value;
    if (this.searchType === "bysearch") {
      this.productList = [];
    } else {
      //All category dropdown
      this.getProductList(true);
    }
  }

  getSearchWord(ev: any): void {
    setTimeout(() => {
      this.userInputUpdate.next(this.searchKeyword);
    }, 100);
  }

}