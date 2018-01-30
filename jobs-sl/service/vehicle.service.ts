import {BusinessErrorException} from '../errors/business-error-exception';
import {format, isBoolean, isNullOrUndefined, isNumber} from 'util';
import {VehicleQuery} from '../criteria/vehicle-query';
import {VehicleModel} from '../model/vehicle.model';
import {PageQuery} from '../model/page-query';
import {BaseService} from './base.service';
import {isEmpty} from 'lodash';
import {v4} from 'uuid';

export class VehicleService extends BaseService {

    private static TOTAL_RESULT_PER_PAGE = 5;

    private static VEHICLE_NOT_FOUND = -1;

    private vehicles: Array<VehicleModel> = [];

    getVehicleByQuery(query: VehicleQuery): Promise<PageQuery<VehicleModel>> {

        this.logger.log('info', format('Find vehicle by query: %j', query));

        if (isNullOrUndefined(query) || (isNullOrUndefined(query.fulltext))) {
            return Promise.reject(new BusinessErrorException('Year and model are required', 400));
        }

        const vehicles = this.vehicles.filter((result: VehicleModel) => {
            return !isNullOrUndefined(result) && (
                result.brand.match(new RegExp(query.fulltext, 'i')) ||
                result.model.match(new RegExp(query.fulltext, 'i')) ||
                result.cor.match(new RegExp(query.fulltext, 'i')) ||
                result.year.toString().match(new RegExp(query.fulltext, 'i'))
            );
        });

        if (isEmpty(vehicles)) {
            return Promise.reject(new BusinessErrorException('No vehicles found', 204));
        }

        const step = (query.page || 0) * VehicleService.TOTAL_RESULT_PER_PAGE;

        return Promise.resolve({
            totals: vehicles.length,
            results: vehicles.slice(step, (step + VehicleService.TOTAL_RESULT_PER_PAGE) - 1)
        })

    }

    getVehicleById(id: string): Promise<VehicleModel> {

        this.logger.log('info', format('Load vehicle by id: %s', id));

        const vehicles = this.vehicles.filter((result: VehicleModel) => {
            return !isNullOrUndefined(result) && result.id === id;
        });

        if (isEmpty(vehicles)) {
            return Promise.reject(new BusinessErrorException('Vehicle not found!', 204));
        }

        return Promise.resolve(<VehicleModel>vehicles.pop());

    }

    saveVehicle(vehicle: VehicleModel): Promise<VehicleModel> {

        if (isEmpty(vehicle)) {
            return Promise.reject(new BusinessErrorException('Vehicle required!'));
        }

        if (isNullOrUndefined(vehicle.brand) || vehicle.brand.length > 40) {
            return Promise.reject(new BusinessErrorException('Mark of the vehicle is required, maximum length: 40!'));
        }

        if (isNullOrUndefined(vehicle.model) || vehicle.model.length > 50) {
            return Promise.reject(new BusinessErrorException('Vehicle model is required, maximum length: 50!'));
        }

        if (isNullOrUndefined(vehicle.cor) || vehicle.cor.length > 30) {
            return Promise.reject(new BusinessErrorException('Vehicle cor is required, maximum length: 30!'));
        }

        if (isNullOrUndefined(vehicle.price) || !isNumber(parseFloat(vehicle.price.toString()))) {
            return Promise.reject(new BusinessErrorException('Price of the vehicle is mandatory!'));
        }

        if (isNullOrUndefined(vehicle.year) || !isNumber(parseInt(vehicle.year.toString(), 0))) {
            return Promise.reject(new BusinessErrorException('Year of the vehicle is required, maximum length: 4!'));
        }

        if (isNullOrUndefined(vehicle.new) || !isBoolean(vehicle.new)) {
            return Promise.reject(new BusinessErrorException('Situation of the vehicle is obligatory'));
        }

        const vehicles = this.vehicles.filter((result: VehicleModel) => {
            return !isNullOrUndefined(result) && result.id === vehicle.id;
        }).map((result: VehicleModel) => {
            return Object.assign(result, vehicle, {updated: new Date()});
        });

        if (isEmpty(vehicles)) {
            this.vehicles.push(Object.assign(vehicle, {
                id: v4(), created: new Date()
            }));
        }

        return Promise.resolve(vehicle);

    }

    updateVehicle(vehicle: VehicleModel): Promise<VehicleModel> {

        if (isEmpty(vehicle)) {
            return Promise.reject(new Error('Vehicle required!'));
        }

        if (isEmpty(vehicle.id)) {
            return Promise.reject(new BusinessErrorException('Vehicle ID required!'));
        }

        return this.saveVehicle(vehicle);

    }

    deleteVehicle(id: string): Promise<void> {

        const vehicle = this.vehicles.findIndex((result: VehicleModel) => {
            return !isNullOrUndefined(result) && result.id === id;
        });

        if (isNullOrUndefined(vehicle) || VehicleService.VEHICLE_NOT_FOUND === vehicle) {
            return Promise.reject(new BusinessErrorException('Vehicle not found!', 204));
        }

        this.vehicles.splice(vehicle, 1);

        return Promise.resolve();

    }

}
