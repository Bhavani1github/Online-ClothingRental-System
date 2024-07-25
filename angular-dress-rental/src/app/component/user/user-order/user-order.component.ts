import { DatePipe } from '@angular/common';
import { Component, NgZone, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';

import { take } from 'rxjs';
import { Order } from '../../model/order.model';
import { DressRentalService } from 'src/app/dress-rental.service';

declare var Razorpay: any;




@Component({
  selector: 'app-user-order',
  templateUrl: './user-order.component.html',
  styleUrls: ['./user-order.component.css']
})
export class UserOrderComponent implements OnInit {

  orderList: Order[]=[];
  selectedOrder: Order | undefined;
  user: any = {};
  constructor(
    private bservice: DressRentalService,
    private router: Router,
    private datePipe : DatePipe,
    private dialog:MatDialog,
    private ngZone: NgZone
  ) { 
    this.bservice.isUserLoginPresent();
  }

  ngOnInit(): void {
    this.getUserDetail();
    this.getOrderList();
  }

  getUserDetail(): void {
    const cid = this.bservice.getUserAuthorization();
    this.bservice.getUserById(cid).pipe(take(1)).subscribe(
      (res: any) => {
        console.log("User*****", res);
        if (!!res && res?.id) {
          this.user = res;
        }
      }, err => {
        console.log("Err");
      }
    )
  }


  getOrderList():void{
    this.bservice.orderList(this.bservice.getUserAuthorization()).pipe(take(1)).subscribe(
      (res: any) => {
       
        if(!!res && Array.isArray(res)){
          this.orderList=res;
         
         
          
        }
        
      }, err => {
        console.log("Error");
      }
    )
  }
  getDate(d:string|undefined):any{
   
    let ans :any;
     console.log("DDDDDD",d);
    if(!!d && d!== null){
      ans=this.datePipe.transform(d,"shortDate")||null;
      console.log("@@@@@@@@",ans);
    }
    return ans;
  }
  
  addPayment(order: Order): void {
    
    this.bservice.addPaymentOfOrder(order?.totalPrice).pipe(take(1)).subscribe((res: any) => {
      console.log('>>>>12333', res);
      console.log('****');
      if (res && res?.orderId) {
        this.openTransactionModel(res);
        this.selectedOrder = order;
      }
     
    }, error => {
      console.log("Error => ", error);
    })
  }

  openTransactionModel(response: any) {
    var options = {
      order_id: response.orderId,
      key: response.key,
      amount: response.amount,
      currency: response.currency,
      name: 'online Rental Dress',
      description: 'Payment of online RentalDress',
      image: 'https://cdn.pixabay.com/photo/2023/01/22/13/46/swans-7736415_640.jpg',
      handler: (response: any) => {
        console.log('####', response);
        if(response!= null && response.razorpay_payment_id != null) {
          this.processResponse(response);
        } else {
          alert("Payment failed..")
        }
       
      },
      prefill : {
        name:'LPY',
        email: 'LPY@GMAIL.COM',
        contact: '9004380734'
      },
      notes: {
        address: 'Online Dress Rental'
      },
      theme: {
        color: '#F37254'
      }
    };

    var razorPayObject = new Razorpay(options);
    razorPayObject.open();
  }

  processResponse(resp: any) {
    
    console.log('>>>>>>>>>>>>>>>>>>>>>>>>>>',resp);
    if (resp && resp?.razorpay_order_id && resp?.razorpay_payment_id && this.selectedOrder) {
      const body: any = {
        totalPrice: this.selectedOrder?.totalPrice,
        orderId: this.selectedOrder?.orderId,
       
        PaidDate: this.datePipe.transform(new Date(), 'yyyy-MM-dd')?.toString(),
        paidAmount: this.selectedOrder?.totalPrice,
        user: this.user
  
  
      };
      console.log("$$$$$$$", body);
      this.bservice.addPayment(body, this.selectedOrder?.orderId, this.user?.id).pipe(take(1)).subscribe(
        (res: any) => {
          console.log("*********", res);
          if (res && res?.paymentId) {
            alert("Payment done sucessfulyy");
            this.ngZone.run(() => {
              this.getOrderList();
            });
            
          }
        }, err => {
          console.log("error");
        }
      )
    }
  }

}
