import {VehicleService} from '../../../../service/vehicle.service';
import {NextFunction, Request, Response, Router} from 'express';
import {VehicleModel} from '../../../../model/vehicle.model';
import {PageQuery} from '../../../../model/page-query';

const service = new VehicleService();

module.exports = function (router: Router) {

    router.get('/', (req: Request, res: Response, next: NextFunction) => {

        service.getVehicleByQuery(req.query).then((vehicles: PageQuery<VehicleModel>) => {
            res.status(200)
                .header('Content-Type', 'application/json')
                .header('Content-Version', 'v1')
                .send(vehicles);
        }).catch((error: any) => {
            next(error);
        });

    });

    router.get('/:id', (req: Request, res: Response, next: NextFunction) => {

        service.getVehicleById(req.params.id).then((vehicle: VehicleModel) => {
            res.status(200)
                .header('Content-Type', 'application/json')
                .header('Content-Version', 'v1')
                .send(vehicle);
        }).catch((error: any) => {
            next(error);
        });

    });

    router.post('/', (req: Request, res: Response, next: NextFunction) => {

        service.saveVehicle(req.body).then((vehicle: VehicleModel) => {
            res.status(200)
                .header('Content-Type', 'application/json')
                .header('Content-Version', 'v1')
                .send(vehicle);
        }).catch((error: any) => {
            next(error);
        });

    });

    router.put('/', (req: Request, res: Response, next: NextFunction) => {

        service.updateVehicle(req.body).then((vehicle: VehicleModel) => {
            res.status(200)
                .header('Content-Type', 'application/json')
                .header('Content-Version', 'v1')
                .send(vehicle);
        }).catch((error: any) => {
            next(error);
        });

    });

    router.delete('/:id', (req: Request, res: Response, next: NextFunction) => {

        service.deleteVehicle(req.params.id).then(() => {
            res.status(200)
                .header('Content-Type', 'application/json')
                .header('Content-Version', 'v1')
                .send({});
        }).catch((error: any) => {
            next(error);
        });

    });

};
