import { Component, OnInit } from '@angular/core';
import { VehicleService } from '../services/vehicle.service';
import { AuthenticationService } from '../security/authentication.service';
import { JwtInfo } from '../security/jwt-info';
import { Role } from '../security/role.enum';

@Component({ templateUrl: './vehicles.component.html' })
export class VehiclesComponent implements OnInit {

   currentUser: JwtInfo;

   constructor(
      private service: VehicleService,
      private authenticationService: AuthenticationService) {
      this.authenticationService.currentUser.subscribe(res => this.currentUser = res);
   }

   ngOnInit() { 
      this.service.refreshList();
   }

   get isAdmin() {
      return this.currentUser && this.currentUser.authorities.some(res => res === Role.Admin);
   }

   onDelete(id: number) {
      if (this.isAdmin && confirm('Você tem certeza que deseja excluir este registro?')) {
         this.service.delete(id).subscribe(res => {
            alert('Registro excluido com sucesso!');
            this.service.refreshList();
         });
      }
   }
}
