import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { VehicleService } from 'src/app/services/vehicle.service';
import { Vehicle } from 'src/app/models/vehicle.model';

@Component({ templateUrl: './vehicle-details.component.html'})
export class VehicleDetailsComponent implements OnInit {

   vehicle: Vehicle;

   constructor(
      private router: Router,
      private route: ActivatedRoute, 
      private vehicleService: VehicleService) { }

   ngOnInit() {
      let vehicleId = this.route.snapshot.paramMap.get('vehicleId');
      if(vehicleId) {
         this.vehicleService.getById(+vehicleId)
            .subscribe(vehicle => { 
               this.vehicle = vehicle;
            }, err => {
               alert("Desculpe! Não foi possível atender esta requisição.");
               this.router.navigate(['vehicles']);
            });
      }
   }

}
