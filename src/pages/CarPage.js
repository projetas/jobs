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
        <td><a href={"/cars/"+this.props.car.id}>{this.props.car.model}</a></td>
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
  getInitialState: function() {

    var save = function (objectSave) {
      //console.log(objectSave);
      $.ajax({
        url: "car/",
        type: 'POST',
        data: objectSave,
        success: function(result) {
          alert('Adicionado com sucesso');
          window.location = "/";
        },
        error: function(xhr, ajaxOptions, thrownError) {
          toastr.error(xhr.responseJSON.message);
        }
      });
    };

    var search = function (objectSearch) {
      var query = "?";

      if(objectSearch.brand != null && !('' == objectSearch.brand) ){
        query = query + "brand=" + objectSearch.brand + "&";
      }
      if(objectSearch.model != null && !('' == objectSearch.model) ){
        query = query + "model=" + objectSearch.model + "&";
      }
      if(objectSearch.color != null && !('' == objectSearch.color) ){
        query = query + "color=" + objectSearch.color + "&";
      }
      if(objectSearch.year != null && !('' == objectSearch.year) ){
        query = query + "maxYear=" + objectSearch.year + "&";
      }
      if(objectSearch.price != null && !('' == objectSearch.price) ){
        query = query + "maxPrice=" + objectSearch.price + "&";
      }
      if(objectSearch.isNew != null && !('' == objectSearch.isNew) ){
        query = query + "isNew=" + objectSearch.isNew + "&";
      }
      query = query.substring(0, query.length -1);
      alert("redirecionando para os resultados!!!");
      window.location = "/"+query;
    };
    return {save: save, search: search};
  },
  render: function() {
    var rows = [];
    this.props.cars.forEach(function(car) {
      rows.push(<Car car={car} />);
    });
    return (
      <div>
        <CarForm buttonName={"Adicionar"} functionSubmit={this.state.save}/>
        <CarForm buttonName={"Buscar"} functionSubmit={this.state.search}/>
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
    this.props.functionSubmit(this.state);
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
          <select className="form-control" onChange={this.handleChangeIsNew} value={this.state.isNew}>
        ]   <option value="">Vazio</option>
            <option value="true">Sim</option>
            <option value="false">Não</option>
          </select>
          <input type="text" className="form-control" placeholder="Preço"
            value={this.state.price} onChange={this.handleChangePrice} />
          <button type="submit" className="btn btn-primary">{this.props.buttonName}</button>
        </div>
      </form>
    );
  }
});


var CarPage = React.createClass({

  loadCarsFromServer: function () {
    var self = this;

    $.ajax({
      url: "http://localhost:8090/car" + this.props.location.search,
      dataType: "json",
      success: function(data) {
        self.setState({cars: data});
      },
      error: function(xhr, ajaxOptions, thrownError) {
        toastr.error(xhr.responseJSON.message);
      }
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
