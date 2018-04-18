import React from 'react';
import $ from 'jquery';
import toastr from 'toastr';

var Car = React.createClass({
  getInitialState: function() {
    return {display: true };
  },
  handleDelete() {
    var self = this;
    $.ajax({
      url: "http://localhost:8090/car/" + this.props.car.id ,
      type: 'DELETE',
      success: function(result) {
        self.setState({display: false});
        location.reload();
      },
      error: function(xhr, ajaxOptions, thrownError) {
        toastr.error(xhr.responseJSON.message);
      }
    });
  },
  render: function() {
    return (
      <tr>
        <td>{this.props.car.brand}</td>
        <td>{this.props.car.model}</td>
        <td>{this.props.car.year}</td>
        <td>{this.props.car.color}</td>
        <td>{this.props.car.isNew ? 'Sim' : 'Não'}</td>
        <td>{this.props.car.price.toFixed(2)}</td>
        <td>{this.props.car.createdDate}</td>
        <td>{this.props.car.updatedDate}</td>
        <td>
          <button className="btn btn-info" onClick={this.handleDelete}>Delete</button>
        </td>
      </tr>
    );
  }
});


var CarTable = React.createClass({
  render: function() {
    var rows = [];
    this.props.cars.forEach(function(car) {
      rows.push(<Car car={car} />);
    });
    return (
      <div>
        <CarForm />
        <table className="table table-striped">
          <thead>
            <tr>
              <th>Marca</th>
              <th>Modelo</th>
              <th>Ano</th>
              <th>Cor</th>
              <th>Novo</th><
              th>Preço</th>
              <th>Criado em:</th>
              <th>Atualizado em:</th>
            </tr>
          </thead>
          <tbody>{rows}</tbody>
        </table>
      </div>
    );
  }
});

var CarForm = React.createClass({
  getInitialState: function () {
    return {brand:'', model:'', year:'', color:'', isNew:'', price:'', description:''};
  },
  handleChangeBrand(event) {
    this.setState({brand: event.target.value});
  },
  handleChangeModel(event) {
    this.setState({model: event.target.value});
  },
  handleChangeYear(event) {
    this.setState({year: event.target.value});
  },
  handleChangeColor(event) {
    this.setState({color: event.target.value});
  },
  handleChangeIsNew(event) {
    this.setState({isNew: event.target.value});
  },
  handleChangePrice(event) {
    this.setState({price: event.target.value});
  },
  handleSubmit(event) {
    $.ajax({
      url: "car/",
      type: 'POST',
      data: this.state,
      success: function(result) {
        alert('Adicionado com sucesso');
        location.reload();
      },
      error: function(xhr, ajaxOptions, thrownError) {
        toastr.error(xhr.responseJSON.message);
      }
    });
    event.preventDefault();
  },
  render: function() {
    return (
      <form className="form-inline" method="POST" onSubmit={this.handleSubmit} style={{margin: '20px 0'}}>
        <div className="form-group">
          <input type="text" className="form-control" placeholder="Marca"
            value={this.state.brand} onChange={this.handleChangeBrand}/>
          <input type="text" className="form-control" placeholder="Modelo"
            value={this.state.model} onChange={this.handleChangeModel}/>
          <input type="text" className="form-control" placeholder="Ano"
            value={this.state.year} onChange={this.handleChangeYear} />
          <input type="text" className="form-control" placeholder="Cor"
            value={this.state.color} onChange={this.handleChangeColor} />
          <input type="text" className="form-control" placeholder="Novo"
            value={this.state.isNew} onChange={this.handleChangeIsNew} />
          <input type="text" className="form-control" placeholder="Preço"
            value={this.state.price} onChange={this.handleChangePrice} />
          <button type="submit" className="btn btn-primary">Adicionar</button>
        </div>
      </form>
    );
  }
});


var CarPage = React.createClass({

  loadCarsFromServer: function () {
    var self = this;
    $.ajax({
      url: "http://localhost:8090/car",
      dataType: "json"
    }).then(function (data) {
      self.setState({cars: data});
    });
  },

  getInitialState: function () {
    return {cars: []};
  },

  componentDidMount: function () {
    this.loadCarsFromServer();
  },

  render() {
    return (
      <CarTable cars={this.state.cars}/>
    );
  }
});

export default CarPage;
