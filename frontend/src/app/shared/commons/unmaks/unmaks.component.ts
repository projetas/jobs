import { Component, Injectable, OnInit } from '@angular/core';

@Injectable()
export class UnMaskComponent {

  constructor() { }

  public unMaskNumber(num) {
    return num.replace(/[^0-9.]/g, '');
  }

  public unMaskInteger(num) {
    return num.replace(/[^0-9]/g, '');
  }

  public maskCep(value: string) {
    return value.replace(/^(\d{5})(\d{3})/, '$1-$2');
  }

}
