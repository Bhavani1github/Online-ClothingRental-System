import { Product } from "./product.model";

// enum ClothingSize {
//     XS = "XS",
//     S = "S",
//     M = "M",
//     L = "L",
//     XL = "XL",
//     XXL = "XXL"
// }

export interface Cart{
    cartId : number;
    mrpPrice : number;
    quantity : number;
    size?:string;
    duration?: string;
    user : any;
    product: Product
    
}