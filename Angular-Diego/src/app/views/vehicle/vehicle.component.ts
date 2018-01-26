import { VehicleService } from './../../service/vehicle.service';
import { Vehicle } from './../../models/vehicle';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from "rxjs";
import { FormGroup, FormBuilder } from '@angular/forms';

@Component({
  templateUrl: 'vehicle.component.html'
})
export class VehicleComponent implements OnInit {

    vehicles = new Array<Vehicle>();
    subscription: Subscription;
    searchForm: FormGroup;
  
    constructor(private router: Router,
      private vehicleService: VehicleService,
      private fb: FormBuilder      
    ) {
      this.searchForm = fb.group({
        model: null
      });
    }
  
    ngOnInit() {
      this.search();
    }

    search() {
    
      if (this.subscription !== undefined) {
        this.subscription.unsubscribe();
      }

      if (this.searchForm.value.model) {
        this.subscription = this.vehicleService.find(this.searchForm.value)
          .subscribe(vehicles => { this.vehicles = vehicles });    
      } else {
         this.subscription = this.vehicleService.find('')
          .subscribe(vehicles => { this.vehicles = vehicles });
      }
    }

    delete(vehicle: Vehicle) {
      this.vehicleService.delete('/' + vehicle.id).subscribe();
      this.search();
    }
    
    edit(vehicle: Vehicle) {
      this.router.navigate(['vehicle/edit', vehicle.id]);
    }
}
