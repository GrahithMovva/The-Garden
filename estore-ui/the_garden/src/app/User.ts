import { Plant } from "./Plants/Plant";

export interface User{
    id: number;
    username: String;
    password: String;
    cart: Plant[];
    

}
