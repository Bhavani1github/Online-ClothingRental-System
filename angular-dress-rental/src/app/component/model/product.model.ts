import { ProductCategry } from "./productCategory.model";
import { ProductSubCategry } from "./productSubCategory.modal";

export interface Product{
    pid: number;
    pname: string;
    pDescription : string;
    pPrice : number;
    pQuantity :number;
    pImage: string;
    mrpPrice: number;
    productCategory: ProductCategry;
    subCategory: ProductSubCategry;
    size?:string;
    duration?: string;
}
