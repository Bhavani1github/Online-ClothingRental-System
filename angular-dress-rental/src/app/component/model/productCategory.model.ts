import { ProductSubCategry } from "./productSubCategory.modal";

export interface ProductCategry{
    pc_id: number;
    categoryName: string;
    subCategories: Array<ProductSubCategry>;
}