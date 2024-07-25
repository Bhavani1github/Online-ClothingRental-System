import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { take } from 'rxjs';
import { DressRentalService } from 'src/app/dress-rental.service';
import { ProductCategry } from '../../model/productCategory.model';
import { ProductSubCategry } from '../../model/productSubCategory.modal';
import { Product } from '../../model/product.model';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent {
  pname: string = '';
  pImage: string = '';
  pDescription: string = '';
  mrpPrice: number = 0;
  isEdit: boolean = false;
  pPrice: number = 0;
  pQuantity: number = 0;
  pid: number = 0;
  catgoryList: Array<ProductCategry> = [];
  subCategoryList: Array<ProductSubCategry> = [];
  categoryId: number = 0;
  subCategoryId: number = 0;

  constructor(

    private bservice: DressRentalService,
    private router: Router,
    private activateRouter: ActivatedRoute
  ) {
    this.activateRouter.queryParams.subscribe((params: any) => {
      if (params?.id) {
        this.isEdit = true;
        this.bservice.getProductById(params?.id).pipe(take(1)).subscribe((res:any)=> {
          if(!!res && res){
            this.getCategoryList();
            setTimeout(() => {
              this.pid = res?.pid;
              this.pname = res?.pname;
              this.pImage = res?.pImage;
              this.pDescription = res?.pDescription;
              this.mrpPrice = res?.mrpPrice;
              this.pPrice = res?.pPrice;
              this.pQuantity = res?.pQuantity;
              this.categoryId = res?.productCategory?.pc_id;
              this.subCategoryId = res?.subCategory?.psc_id;
              this.getSubList();
            }, 100);
           
          }
          console.log(res);
        });
      }

    })
  }
  ngOnInit(): void {
    this.bservice.isUserLoginPresent();
    this.getCategoryList();
  }

  getCategoryList(): void {
      this.bservice.getAllCategoryList()
        .pipe(take(1))
        .subscribe((res: any) => {
          if (res && Array.isArray(res)) {
            this.catgoryList = res;
          }
        });
  }

  onAddProduct(): void {
   
    if (this.pname === '') {
      alert("Product Nme is required");
      return;
    }
    if (this.pDescription === '') {
      alert("description  is required");
      return;
    }

    if (this.pImage === '') {
      alert("Image should not be blank");
      return;
    }
    console.log("******MRP price",this.mrpPrice);
    if (this.mrpPrice === 0 || this.mrpPrice===null) {
      alert("MRP Price should not be zero/blank");
      return;
    }
    if (this.pQuantity === 0|| this.pQuantity===null || this.pQuantity <0) {
      alert("Quantity should not be zero/blank and negative");
      return;
    }
    
 

    const body: any = {
      pname: this.pname,
      pImage: this.pImage,
      pDescription: this.pDescription,
      mrpPrice: this.mrpPrice,
      pQuantity: this.pQuantity,
      productCategory: {pc_id: this.categoryId},
      subCategory: {psc_id: this.subCategoryId}
    };
    console.log('>>>', body)
    
    if(this.isEdit){
      console.log("=======>", body);
    this.bservice.editProduct(body,this.pid).pipe(take(1)).subscribe((res: any) => {
      console.log("*****", res);
      if (res && res?.pid) {
        alert("Product updated sucessfully");
        this.router.navigate(["/admin/productlist"]);
      }
    }, err => {
      console.log("Error  ", err);
      alert("Something going wrong!! Please try again");
    })
    }else{
      console.log("=======>", body);
      this.bservice.addProduct(body).pipe(take(1)).subscribe((res: any) => {
        console.log("*****", res);
        if (res) {
          alert("Product added sucessfully");
          this.router.navigate(["/admin/home"]);
        }
      }, err => {
        console.log("Error  ", err);
        alert("Something going wrong!! Please try again");
      })
    }
  }

  getSubList(): void {
    console.log(this.catgoryList.map((item: ProductCategry) => item?.pc_id) [0]=== parseInt(this.categoryId.toString(), 10));
    if (this.categoryId) {
      console.log('>xx>>', this.catgoryList.filter((item: ProductCategry) => parseInt(item?.pc_id.toString(), 10) === parseInt(this.categoryId.toString(), 10)));
      const subList = this.catgoryList.filter((item: ProductCategry) => parseInt(item?.pc_id.toString(), 10) === parseInt(this.categoryId.toString(), 10));
      console.log('>>>>>', subList);
      if (subList ) {
        this.subCategoryList = subList[0].subCategories;
      }
    }
  }

}
