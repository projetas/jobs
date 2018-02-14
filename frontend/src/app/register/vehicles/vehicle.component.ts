import { Component, OnInit } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Pipe, PipeTransform } from '@angular/core';
import * as moment from 'moment'

import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { VehicleService } from './vehicle.service';
import { VehicleViewModel } from '../viewModels/vehicle.view-model';

@Component({
  selector: 'app-vehicles',
  templateUrl: './vehicle.component.html',
  providers: [VehicleService]
})
export class VehicleComponent implements OnInit {

  vehicleData: VehicleViewModel = new VehicleViewModel({});
  executePost: boolean = true;
  vehicleForm: FormGroup;
  vehicle_id: number;
  inputPrice: any;

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private vehicleService: VehicleService,
    private rota: ActivatedRoute

  ) { this.vehicle_id = rota.snapshot.params['id']; }

  ngOnInit() {
    this.buildForm();
    this.getVehicleById();
  }

  buildForm() {
    this.vehicleForm = this.formBuilder.group({
      'brand': ['', Validators.required],
      'model': ['', Validators.required],
      'color': ['', Validators.required],
      'year': ['', Validators.required],
      'price': ['', Validators.required],
      'description': [''],
      'is_new': ['', Validators.required]
    });
  }


  getVehicleById() {
debugger
    const idVehicle = this.vehicle_id;
    this.vehicleService.getVehicleById(idVehicle).subscribe(
      (data: any) => {
        if (data !== undefined) {
          if (data.id !== null) {
            this.vehicleData = new VehicleViewModel(data);
            this.vehicleData.id = parseInt(data.id, 10);
            this.inputPrice = data.price;

          }
        }
      },
      err => {
        const errorSave = err;
        if (errorSave.errors != null) {
          if (errorSave.errors[0].Status === 404) {
            this.executePost = true;
          }
        }
      });
  }

  unMaskMoney(value: string) {
    return value.toString().replace(/[^0-9,]/g, '').replace(',', '.');
  }
  saveData() {
debugger
    if (this.vehicleData.id != null) {
      this.executePost = false;
    }

    if(this.inputPrice != null) {
      this.vehicleData.price = parseInt(this.unMaskMoney(this.inputPrice));
    }   
    console.log(this.vehicleData.price);
    if (this.executePost) {
      this.vehicleService.postVehicle(this.vehicleData).subscribe(result => {
        if (result) {
          const dataReturn = result.Data;
          this.nextRoute();
        }
      });
    }

    else {
      if (this.executePost === false) {
        if (this.vehicleData.id != null) {
          this.vehicleService.updateVehicle(this.vehicleData).subscribe(res => {
            if(res){
              this.nextRoute();
            }
          });
        }
      }
    }
  }

  submitUpdate() {
    this.vehicleService.updateVehicle(this.vehicleData).subscribe(result => {
      alert("Atualizado com sucesso");
    }, error => {
      var data = JSON.parse(error._body);
    });
  }

  nextRoute() {
    this.router.navigate(['/vehicleList']);
  }
  validContinue() {
    if (this.vehicleForm.valid) {
      this.saveData();
    }
  }


}
