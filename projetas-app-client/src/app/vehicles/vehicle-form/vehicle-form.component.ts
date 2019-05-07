import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { VehicleService } from 'src/app/services/vehicle.service';
import { Vehicle } from 'src/app/models/vehicle.model';

@Component({ templateUrl: './vehicle-form.component.html' })
export class VehicleFormComponent implements OnInit {

   vehicleForm: FormGroup;
   formData: Vehicle;
   submitted = false;

   constructor(
      private router: Router,
      private route: ActivatedRoute,
      private formBuilder: FormBuilder,
      private vehicleService: VehicleService) { }

   ngOnInit() {
      let vehicleId = this.route.snapshot.paramMap.get('vehicleId');
      if(vehicleId) {
         this.vehicleService.getById(+vehicleId)
            .subscribe(vehicle => { 
               this.formData = vehicle;
               this.configFormData();
            }, err => {
               alert("Desculpe! Não foi possível atender esta requisição.");
            });
      } else {
         this.formData = new Vehicle();
         this.configFormData();
      }
   }

   configFormData() {
      if (this.vehicleForm) {
         this.vehicleForm.reset();
      }
      this.vehicleForm = this.formBuilder.group({
         id:               [this.formData.id],
         brand:            [this.formData.brand,       [Validators.required, Validators.maxLength(40)]],
         model:            [this.formData.model,       [Validators.required, Validators.maxLength(50)]],
         color:            [this.formData.color,       [Validators.required, Validators.maxLength(30)]],
         year:             [this.formData.year,        [Validators.required, Validators.min(1000), Validators.max(9999)]],
         price:            [this.formData.price,       [Validators.required, Validators.min(0)]],
         description:      [this.formData.description],
         isNew:            [this.formData.isNew,       [Validators.required]],
         saveDate:         [this.formData.saveDate],
         updateDate:       [this.formData.updateDate],
      });
   }

   onSubmit() {
      this.submitted = true;
      if (this.vehicleForm.invalid) {
         this.submitted = false;
         return;
      }
      if (!this.vehicleForm.value.id) {
         this.vehicleService.save(this.vehicleForm.value).subscribe(res => {
            alert('Registro cadastrado com sucesso!');
            this.redirectToVehicles();
         }, err => {
            alert("Desculpe! Não foi possível salvar o registro.");
         });
      } else {
         this.vehicleService.update(this.vehicleForm.value).subscribe(res => {
            alert('Registro atualizado com sucesso!');
            this.redirectToVehicles();
         }, err => {
            alert("Desculpe! Não foi possível atualizar o registro.");
         });
      }
   }

   get f() { 
      return this.vehicleForm.controls; 
   }

   resetForm() {
      this.formData = new Vehicle();
      if (this.vehicleForm) {
         this.vehicleForm.reset();
      }
      this.submitted = false;
   }

   redirectToVehicles() {
      this.resetForm();
      this.router.navigate(['vehicles']);
   }
}
