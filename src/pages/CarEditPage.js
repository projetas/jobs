import React from 'react';
import $ from 'jquery';
import toastr from 'toastr';

var CarForm = React.createClass({

  loadCarFromServer: function (idCar) {
    var self = this;
    $.ajax({
      url: "http://localhost:8090/car/" + idCar,
      dataType: "json",
      success: function(data) {
        self.setState(data);
      },
      error: function(xhr, ajaxOptions, thrownError) {
        toastr.error(xhr.responseJSON.message);
      }
    });
  },
  componentDidMount: function () {
    this.loadCarFromServer(this.props.id);
  },
  getInitialState: function () {
    return {brand:'', model:'', year:'', color:'', isNew:'', price:'', description:'', updatedDate: undefined};
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
  handleChangeDescription(event) {
    this.setState({description: event.target.value});
  },
  handleSubmit(event) {
    delete this.state['updatedDate'];
    $.ajax({
      url: "http://localhost:8090/car/",
      type: 'PUT',
      data: this.state,
      success: function(result) {
        alert('Atualizado com sucesso');
        window.location.href = '/';
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
          <div className="margin-botton">
            <div className="col-md-2">
              <label for="brand">Marca</label><br/>
              <input id="brand" type="text" className="form-control" placeholder="Marca"
              value={this.state.brand} onChange={this.handleChangeBrand}/>
            </div>
            <div className="col-md-2">
              <label for="model">Modelo</label><br/>
              <input id="model" type="text" className="form-control" placeholder="Modelo"
              value={this.state.model} onChange={this.handleChangeModel}/>
            </div>
            <div className="col-md-2">
              <label for="year">Ano</label><br/>
              <input id="year" type="text" className="form-control" placeholder="Ano"
              value={this.state.year} onChange={this.handleChangeYear} />
            </div>
            <div className="col-md-2">
              <label for="color">Cor</label><br/>
              <input id="color" type="text" className="form-control" placeholder="Cor"
              value={this.state.color} onChange={this.handleChangeColor} />
            </div>
            <div className="col-md-2">
              <label for="isNew">Novo</label><br/>
              <select id="isNew"  className="form-control width-100" onChange={this.handleChangeIsNew} value={this.state.isNew}>
                <option value="true">Sim</option>
                <option value="false">Não</option>
              </select>
            </div>
            <div className="col-md-2">
              <label for="price">Preço</label><br/>
              <input id="price" type="text" className="form-control" placeholder="Preço"
              value={this.state.price} onChange={this.handleChangePrice} />
            </div>
          </div>
          <div>
            <div className="col-md-2">
              <label for="createdDate">Criado em</label><br/>
              <input id="createdDate" type="text" className="form-control" value={this.state.createdDate} disabled />
            </div>
            <div className="col-md-2">
              <label for="updatedDate">Atualizado em</label><br/>
              <input id="updatedDate" type="text" className="form-control" value={this.state.updatedDate} disabled />
            </div>
            <div className="col-md-6">
              <label for="description">Descrição</label><br/>
              <textarea id="description" type="text" className="form-control width-100" placeholder="Descrição"
              value={this.state.description} onChange={this.handleChangeDescription} />
            </div>
            <div className="col-md-2">
              <br></br>
              <button type="submit" className="btn btn-primary">Atualizar</button>
            </div>
          </div>
        </div>
      </form>
    );
  }
});


var CarEditPage = React.createClass({

  componentWillMount() {
    var self = this;
    const { id } = this.props.match.params;
    self.setState({idCar: id});
  },
  render() {
    return (
      <CarForm id={this.state.idCar}/>
    );
  }
});

export default CarEditPage;
