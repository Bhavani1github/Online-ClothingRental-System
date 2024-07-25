import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DressRentalService {
  url: string = 'http://localhost:8085';
  

  constructor(
    private http:HttpClient,
    private route:Router
  ) { }
   

  /* Client Registeration */
  signUp(body: any): Observable<any> {
    return this.http.post(this.url + "/api/user", body);
  }
  //client login
  userSignIn(body: any): Observable<any> {
    return this.http.post(this.url + "/api/user/login", body);
  }
  //once we logged in that time we are storing client id into token 
  storeUserAuthorization(token: string): void {
    localStorage.setItem("token", token);
  }

  getUserAuthorization(): any {
    const token = localStorage.getItem("token");
    return token;
  }

  storeUserName(name: string): void {
    localStorage.setItem("userName", name);
  }

  getUserName(): any {
    const name = localStorage.getItem("userName");
    return name;
  }

  userLogout(): void {
    localStorage.clear();
    this.route.navigate(['']);
  }
  //admin login
  adminSignIn(body: any): Observable<any> {
    return this.http.post(this.url + "/api/admin/login", body);
  }
  storeAdminAuthorization(token: string): void {
    localStorage.setItem("admin", token);
  }
  getAdminAuthorization(): any {
    const token = localStorage.getItem("admin");
    return token;
  }

  storeAdminUserName(name: string): void {
    localStorage.setItem("adminName", name);
  }

  getAdminName(): any {
    const name = localStorage.getItem("adminName");
    return name;
  }

  adminLogout(): void {
    localStorage.clear();
    this.route.navigate(['/']);
  }

  // this is to get username in admin.home.html part via admin.home.ts
  isAdminLoginPresent(): void {
    if (this.getAdminAuthorization() === null) {
      this.route.navigate(['/admin-login']);
    }
  }

  isUserLoginPresent(): void {
    if (this.getUserAuthorization() === null) {
      this.route.navigate(['/user-login']);
    }
  }

  deleteProduct(id :any):Observable<any> {
     return this.http.delete(this.url + "/api/product/deleteproduct/" +id, { responseType: 'text' });
  }
  
  getProductById(id:any):Observable<any> {
    return this.http.get(this.url + "/api/product/getProductById/"+id);
  }
  
  editProduct(body: any,id:any): Observable<any> {
    return this.http.put(this.url + "/api/product/updateproduct/"+id, body);
  }

  getProductSearch(keyword:any):Observable<any> {
    return this.http.get(this.url + "/api/product/search?name="+keyword);
  }
  



  addToCart(body: any,pid:any,cid:any):Observable<any>{
    return this.http.post(this.url+"/api/cart/"+cid+"/"+pid,body);
  }
  
  getUserById(id:any):Observable<any> {
    return this.http.get(this.url + "/api/user/"+id);
  }
  
  cartList():Observable<any>{
    return this.http.get(this.url+"/api/cart/list");
  }
  placeOrder(cid:any,cartid:any,body:any):Observable<any> {
    return this.http.post(this.url + "/api/orders/"+cid+"/"+cartid, body);
  }
  deleteCart(id :any):Observable<any> {
    
    return this.http.delete(`${this.url}/api/cart/${id}`);
  }
  
  orderList(id:any):Observable<any>{
    return this.http.get(this.url+"/api/orders/"+id);
  }
  
  addPayment(body:any,orderid:any,cid:any):Observable<any> {
    return this.http.post(this.url + "/api/payements/"+orderid+"/"+cid, body);
  }

  forgotPassword(body: any):Observable<any> {
    return this.http.post(this.url + "/api/users/forgotpassword", body);
  }
  
  updateUserInformation(body: any):Observable<any> {
    return this.http.put(this.url + "/api/users/user/"+body?.userId, body);
  }
  
  changePassword(uid: any,password:any):Observable<any> {
    return this.http.post(this.url + "/api/users/"+uid+"/"+password,{});
  }

  getAllorderList():Observable<any>{
    return this.http.get(this.url+"/api/orders");
  }

  placeOrderItem(cid:any, body:any):Observable<any>{
    return this.http.post(this.url + "/api/orders/addOrderItem/"+cid, body);
  }

  addPaymentOfOrder(amount: any):Observable<any> {
    return this.http.get(this.url + "/api/orders/createTransaction/"+amount);
  }

  storeUserRole(role: string): void {
    localStorage.setItem("role", role);
  }

  getUserRole(): any {
    const role = localStorage.getItem("role");
    return role;
  }

  getAllProducts():Observable<any> {
    return this.http.get(this.url + "/api/product/productlist");
  }

  getAllSubCategoryList():Observable<any> {
    return this.http.get(this.url + "/productsubcategory/list");
  }

  getAllCategoryList():Observable<any> {
    return this.http.get(this.url + "/productcategory/list");
  }

  addProduct(body: any):Observable<any>{
    return this.http.post(this.url + "/api/product/addproduct", body, { responseType: 'text' });
  }

  addCategory(body: any):Observable<any>{
    return this.http.post(this.url + "/productcategory/add", body, { responseType: 'text' });
  }

  addSubCategory(body: any):Observable<any>{
    return this.http.post(this.url + "/productsubcategory/add", body, { responseType: 'text' });
  }

  findUserByEmail(email: string):Observable<any> {
    return this.http.get(this.url + "/api/user/findByUserEmail/"+email);
  }

  changeUserPassword(uid: string, password: string):Observable<any> {
    return this.http.post(this.url + "/api/user/changePassword/"+uid+"/"+password, {});
  }
  navigateToLink(path: string): void {
    this.route.navigate([path]);
  }


  getAllClothes(offset: any, limit: any):Observable<any>{
    return this.http.get(this.url+"/api/product" + offset + "/" + limit);
  }

  getProductByPage(pageno: any, size: any):Observable<any>{
    return this.http.get(this.url+"/api/product/" + pageno + "/" + size);
  }

}


  
