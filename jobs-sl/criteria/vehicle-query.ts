export interface VehicleQuery {

    brand: string;
    model: string;
    year: number;
    cor: string;
    new?: boolean;
    page: number;
    fulltext: string;

}
