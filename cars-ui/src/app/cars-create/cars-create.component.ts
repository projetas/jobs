import { Car } from './model';
import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { CarService } from './../car.service';
import { ToastyService } from 'ng2-toasty';


@Component({
  selector: 'app-cars-create',
  templateUrl: './cars-create.component.html',
  styleUrls: ['./cars-create.component.css']
})
export class CarsCreateComponent implements OnInit {

  novos = [
    { label: 'Sim', value: 1 },
    { label: 'Nao', value: 2 },
  ];

  car = new Car();

  constructor(private carService: CarService, private toasty: ToastyService) {
  }

   ngOnInit() {
  }

  salvar(form: FormControl) {
    this.carService.adicionar(this.car)
      .then(() => {
        console.log('Carro adicionado com sucesso!');

        form.reset();
        this.car = new Car();
        this.toasty.success('Carro cadastrado com sucesso!');
      });
  }
}
