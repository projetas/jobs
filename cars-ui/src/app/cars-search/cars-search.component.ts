import { CarService } from './../car.service';
import { Component, OnInit } from '@angular/core';
import { ToastyService } from 'ng2-toasty';


@Component({
  selector: 'app-cars-search',
  templateUrl: './cars-search.component.html',
  styleUrls: ['./cars-search.component.css']
})
export class CarsSearchComponent implements OnInit {

  cars = [];

  constructor(private carService: CarService, private toasty: ToastyService) {

  }

  ngOnInit() {
    this.pesquisar();
  }

  pesquisar() {
    this.carService.pesquisar()
      .then(cars => this.cars = cars);
  }

  excluir(car: any) {
    this.carService.excluir(car.codigo)
      .then(() => {
        this.pesquisar();
        this.toasty.success('Carro excluído com sucesso!');
      });
  }

}
