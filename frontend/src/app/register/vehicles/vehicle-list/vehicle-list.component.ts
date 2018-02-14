import { Component, OnInit } from '@angular/core';
import { VehicleService } from '../vehicle.service';
import { Http } from '@angular/http';

@Component({
  selector: 'app-vehicle-list',
  templateUrl: './vehicle-list.component.html',
  providers: [VehicleService]
})
export class VehicleListComponent implements OnInit {

  mensagem: string = "";
  vehicleList: any[];

  constructor(http: Http, private vehicleService: VehicleService) { }

  ngOnInit() {
    this.vehicleService.getVehicle().subscribe(result => {
      this.vehicleList = result;
    });
  }


  excluirVehicle(person: any) {

    this.vehicleService.deleteVehicle(person.id).subscribe(() => {
      let indicePerson = this.vehicleList.indexOf(person);

      if (indicePerson > -1) {
        this.mensagem = 'Pessoa removido com sucesso.';
        this.vehicleList.slice(indicePerson, 1);
        this.updateList();
      }
    });
  }

  updateList() {

    this.vehicleService.getVehicle().subscribe(result => {
      this.vehicleList = result;
    });
  }

}