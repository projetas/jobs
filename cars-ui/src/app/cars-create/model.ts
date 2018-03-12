export class Car {
  codigo: number;
  descricao: string;
  dataCadastro: Date;
  dataAtualizacao: Date;
  preco: number;
  marca: string;
  cor: string;
  modelo: string;
  ano: number;
  novo: boolean;

  constructor() {
    this.novo = true;
  }

}
