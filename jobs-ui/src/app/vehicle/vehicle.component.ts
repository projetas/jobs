import {debounceTime, distinctUntilChanged, filter, startWith, switchMap} from 'rxjs/operators';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {VehicleService} from '../service/vehicle.service';
import {ReplaySubject} from 'rxjs/ReplaySubject';
import {Component, OnInit} from '@angular/core';
import {HttpParams} from '@angular/common/http';
import {Subscription} from 'rxjs/Subscription';
import {PageQuery} from '../model/page-query';
import {PageEvent} from '@angular/material';
import {Vehicle} from '../model/vehicle';
import {isNullOrUndefined} from 'util';

@Component({
  selector: 'app-vehicle',
  templateUrl: './vehicle.component.html',
  styleUrls: ['./vehicle.component.css']
})
export class VehicleComponent implements OnInit {

  private _vehicles: ReplaySubject<PageQuery<Vehicle>>;

  private _pages: Subscription;

  private _newOrUpdate: boolean;

  private _vehicle: Vehicle;

  findVehicle: FormControl;

  form: FormGroup;

  constructor(private vehicleService: VehicleService, private fb: FormBuilder) {
    this.findVehicle = new FormControl();
    this._vehicles = new ReplaySubject<PageQuery<Vehicle>>();
  }

  ngOnInit() {
    this.listVehicles();
  }

  listVehicles(): void {

    this.newVehicle(false);

    this.findVehicle.valueChanges.pipe(
      startWith(''),
      debounceTime(1000),
      distinctUntilChanged(),
      // filter((term: string) => {
      //   return term && term.length > 3;
      // }),
      switchMap((term: any) => {
        return this.vehicleService.getVehicles(new HttpParams().set('fulltext', term));
      })
    ).subscribe(this._vehicles);

  }

  newVehicle(newOrUpdate): void {

    // this.findVehicle = new FormControl();

    this._newOrUpdate = newOrUpdate;

    this._vehicle = null;

    this.form = this.fb.group({
      brand: ['', Validators.compose([Validators.required, Validators.maxLength(40)])],
      model: ['', Validators.compose([Validators.required, Validators.maxLength(50)])],
      year: ['', Validators.compose([Validators.required, Validators.minLength(4), Validators.maxLength(4)])],
      price: ['', Validators.compose([Validators.required, Validators.pattern(/^\d*\.?\d+$/)])],
      description: '',
      cor: ['', Validators.required],
      new: ['', Validators.required]
    });

  }

  removeVehicle(id: string): void {
    this.vehicleService.removeVehicle(id).subscribe(() => {
      this.ngOnInit();
    });
  }

  onSelecetVehicle(id: string): void {

    this.newVehicle(true);

    this.vehicleService.getVehicle(id).subscribe((vehicle: Vehicle) => {

      this._vehicle = vehicle;

      this.form.setValue({
        brand: vehicle.brand,
        model: vehicle.model,
        year: vehicle.year,
        price: vehicle.price,
        cor: vehicle.cor,
        new: vehicle.new,
        description: vehicle.description
      });

    });

  }

  onSubmit() {

    const newVehicle = isNullOrUndefined(this._vehicle);

    if (newVehicle) {
      this._vehicle = JSON.parse(JSON.stringify(this.form.getRawValue()));
    }

    Object.assign(this._vehicle, this.form.getRawValue());

    if (newVehicle) {
      this.vehicleService.saveVehicle(this._vehicle).subscribe(() => {
        this.ngOnInit();
      }, () => {
        this._vehicle = null;
      });
    } else {
      this.vehicleService.updateVehicle(this._vehicle).subscribe(() => {
        this.ngOnInit();
      });
    }

  }

  navegar(event: PageEvent) {

    if (!isNullOrUndefined(this._pages)) {
      this._pages.unsubscribe();
    }

    this._pages = this.vehicleService.getVehicles(new HttpParams().set('fulltext', '20')
      .set('page', event.pageIndex.toString())).subscribe((page: PageQuery<Vehicle>) => {
      this._vehicles.next(page);
    });

  }

  get vehicles(): ReplaySubject<PageQuery<Vehicle>> {
    return this._vehicles;
  }

  get newOrUpdate(): boolean {
    return this._newOrUpdate;
  }

  get vehicle(): Vehicle {
    return this._vehicle;
  }

}
