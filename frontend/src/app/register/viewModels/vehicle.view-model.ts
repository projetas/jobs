

export class VehicleViewModel {
    id: number;
    brand: string;
    model: string;
    color: string;
    year: number;
    price: number;
    description: string;
    is_new: boolean;


    constructor(v: any) {
        this.id = v.id != null ? v.id : null;
        this.brand = v.brand != null ? v.brand : null;
        this.color = v.color != null ? v.color : null;
        this.model = v.model != null ? v.model : null;
        this.year = v.year ? v.year : null;
        this.price = v.price ? v.price : null;
        this.description = v.description ? v.description : null;
        this.is_new = v.is_new != null ? v.is_new : null;
    }
}

