import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cart } from '../../model/cart.model';
import * as _ from "lodash";
import { forkJoin, take } from 'rxjs';
import { DressRentalService } from 'src/app/dress-rental.service';
import { Product } from '../../model/product.model';

@Component({
  selector: 'app-user-cart',
  templateUrl: './user-cart.component.html',
  styleUrls: ['./user-cart.component.css']
})
export class UserCartComponent implements OnInit {
  cartList: Cart[] = [];
  cartListBackup: Cart[] = [];
  grandTotal: number = 0;
  user: any = {};
 


  constructor(
    private bservice: DressRentalService,
    private router: Router,
    private datePipe: DatePipe
  ) {
    this.bservice.isUserLoginPresent();
    this.getCartList();
    this.getUserDetail();
  }

  ngOnInit(): void {
  }
  getCartList(isDelete?: boolean): void {
    this.bservice.cartList().pipe(take(1)).subscribe(
      (res: any) => {
        console.log("****", res);
        if (!!res && Array.isArray(res)) {
          const userFilter = res.filter((item: Cart)=> item?.user?.id === parseInt(this.bservice.getUserAuthorization()));
          console.log("user filter::::::",userFilter);
          this.cartList = userFilter;
          this.cartListBackup =  _.cloneDeep(userFilter);
          if (this.cartList.length > 0) {
            this.cartList.map((item: Cart) => {
              this.grandTotal += (item?.mrpPrice * item?.quantity);
            });
            if (isDelete) {
              this.updateGrantTotal();
            }
          }
        }
      }, err => {
        console.log("error");
      }

    );
  }
  getTotal(quantity: number = 0, mrpPrice: number = 0): number {
    return quantity * mrpPrice;
  }
   placeOrder(): void {
    let totalPrice: number = 0;
    const deleteCartReq:any[]=[];
    const productItems: Array<Product> = [];
    this.cartList.forEach((item: Cart) => {
      productItems.push(item?.product);
      totalPrice += (item?.mrpPrice * item?.quantity);
      deleteCartReq.push(this.bservice.deleteCart(item?.cartId));
    });
    console.log('>>>>>>>>', totalPrice)
    const body: any = {
      totalPrice: totalPrice,
      orderStatus: "success",
      paymentStatus: "success",
       orderedDate: this.datePipe.transform(new Date(), 'yyyy-MM-dd'),
       user: this.user,
       product: productItems,
       
    };
    this.bservice.placeOrderItem(this.user?.id, body).pipe(take(1)).subscribe((res: any) => {
      console.log('>>>>>>>', res);
     forkJoin(deleteCartReq).pipe(take(1)).subscribe();
      alert("Place order Sucessfully");
      this.router.navigate(["/user/order"]);
    })


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

  deleteCart(cart:Cart, showAlert: boolean = true):void{
    this.bservice.deleteCart(cart?.cartId).pipe(take(1)).subscribe(
      (res: any) => {
        if (showAlert) {
          alert("Clothes deleted sucessfully");
        }
        this.getCartList(true);
      
        
      }, err => {
        console.log("Err");
      }
    )
  }

  onIncreaseQunatity(cart: Cart): void {
    const index = this.cartList.findIndex((item: Cart) => item.cartId === cart?.cartId);
    // const bac = Object.assign(this.cartListBackup);
    const indexBackup = this.cartListBackup.findIndex((item: Cart) => item.cartId === cart?.cartId);
    const qty = cart.quantity + 1;
    console.log( this.cartListBackup[indexBackup].quantity , '>>>>>>' , (cart.product?.pQuantity ))
    if (qty > (cart.product?.pQuantity  + this.cartListBackup[indexBackup].quantity) ) {
      alert('Added quantity should not greater than avaiable quantity');
      return;
    }
    this.cartList[index].quantity = qty;
    this.updateGrantTotal();
  }

  onDecreaseQunatity(cart: Cart): void {
    const index = this.cartList.findIndex((item: Cart) => item.cartId === cart?.cartId);
    const qty = cart.quantity - 1;
    if (qty === 0) {
      this.deleteCart(cart, false);
    }
    this.cartList[index].quantity = qty;
    this.updateGrantTotal();
  }

  updateGrantTotal(): void {
    let total = 0;
    this.cartList.map((item: Cart) => {
      total+= (item?.mrpPrice * item?.quantity);
      console.log("Total--------"+total)
     
    })
    this.grandTotal = total;
    console.log("grandTotal--------"+this.grandTotal)
  }


}
