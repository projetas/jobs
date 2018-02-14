import { navigation } from './../../../_nav';
import { VehicleService } from './../../../service/vehicle.service';
import { Vehicle } from './../../../models/vehicle';

import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from "rxjs";
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Location } from '@angular/common'

@Component({
  templateUrl: 'vehicle-new.component.html'
})
export class VehicleNewComponent implements OnInit {

    subscription: Subscription;
    createForm: FormGroup;
    vehicle: Vehicle;
  
    constructor(private route: ActivatedRoute,
      private router: Router,
      private vehicleService: VehicleService,
      private fb: FormBuilder,
      private location: Location) { 
        this.createForm = fb.group(
          {
            brand: [null, Validators.maxLength(40)],
            model: [null, Validators.maxLength(50)],
            price: [null, Validators.maxLength(10)],
            year: [null, Validators.maxLength(4)],
            description: [null, Validators.maxLength(500)],
            isNew: false
          })
      }
  
    ngOnInit() {
      this.route.data.subscribe((data: any) => {
        console.log(data.vehicle);
        this.vehicle = data.vehicle || new Vehicle();
      });
    }

    getObjectPersiste(): any {
      const id = this.vehicle.id || null;
      const register = this.vehicle.register || null;
      return {
        id: id,
        register: register,
        brand: this.createForm.controls["brand"].value,
        model: this.createForm.controls["model"].value,
        price: this.createForm.controls["price"].value,
        year: this.createForm.controls["year"].value,
        description: this.createForm.controls["description"].value,
        isNew: this.createForm.controls["isNew"].value
      };
    }

    save() {
    
      if (this.subscription !== undefined) {
        this.subscription.unsubscribe(  );
      }

      this.vehicleService.save(this.getObjectPersiste()).subscribe(response =>
        this.router.navigate(['vehicle'])
      );
      
    }
  
}
