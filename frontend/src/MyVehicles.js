import React from 'react';
import moment from 'moment';
import Pubsub from 'pubsub-js';
import {Card, CardHeader} from 'material-ui/Card';
import NumberFormat from 'react-number-format';
import Checkbox from 'material-ui/Checkbox';
import Toggle from 'material-ui/Toggle';
import RaisedButton from 'material-ui/RaisedButton';
import TextField from 'material-ui/TextField';
import {
  Table,
  TableBody,
  TableHeader,
  TableHeaderColumn,
  TableRow,
  TableRowColumn,
} from 'material-ui/Table';

const styles = {
  block: {
    maxWidth: 250,
  },
  toggle: {
    marginBottom: 16,
    maxWidth: 200,
  },
  formHide: {
    display: 'none'
  },
  formShow: {
    display: 'block'
  },
  button: {
    margin: 12,
  }
}

const VEHICLE_SELECTED = 'vehicle-selected';
const VEHICLE_REMOVED = 'vehicle-removed';
const VEHICLES_UPDATED = 'vehicles-updated-clicked';
const INSERT_BUTTON_CLICKED = 'insert-button-clicked';
const EDITION_BUTTON_CLICKED = 'edition-button-clicked';
const REMOVAL_BUTTON_CLICKED = 'removal-button-clicked';
const SAVE_BUTTON_CLICKED = 'removal-button-clicked';


export default class MyVehicles extends React.Component {

  constructor() {
    super();
    this.state = {
      vehicles: [],
      selectedVehicle: {},
      isCreatingVehicle: false,
      isEdittingVehicle: false
    }

  }

  getVehicles() {
    fetch('http://localhost:8080/vehicles')
    .then(result => result.json())
    .then(vehiclesResult => this.setState({vehicles: vehiclesResult}, console.log(vehiclesResult)))
  }

  componentWillMount() {

    Pubsub.subscribe(VEHICLE_SELECTED, (event, vehicle) => {
      this.cleanVehicleSelector();
      if (vehicle !== undefined) {
        vehicle.selected=true;
      }
    })

    Pubsub.subscribe(VEHICLE_REMOVED, (event) => {
      this.getVehicles();
      this.cleanVehicleSelector();
      window.location.reload();
    })

    Pubsub.subscribe(VEHICLES_UPDATED, (event) => {
      this.getVehicles();
      this.cleanVehicleSelector();
      window.location.reload();
    })

    Pubsub.subscribe(INSERT_BUTTON_CLICKED, (event) => {
      this.enableFormFieldsForCreation();
      this.setState({selectedVehicle: {}})
    })

    Pubsub.subscribe(EDITION_BUTTON_CLICKED, (event) => {
      this.enableFormFieldsForEdition();
      let selectedVehicle = this.state.vehicles.filter( (v) => v.selected === true)[0];
      const requestInfo = {
          method: 'GET',
          credentials: "same-origin"
      };
      fetch('http://localhost:8080/vehicle/' + selectedVehicle.id, requestInfo)
        .then(response => response.json())
        .then(vehicle => {
          this.fillEditionVehicle(vehicle);
        })
    })

    Pubsub.subscribe(SAVE_BUTTON_CLICKED, (event) => {
      if (this.state.isCreatingVehicle) {
        this.createVehicle();
      } else if (this.state.isEdittingVehicle) {
        this.updateVehicle();
      } else {
        alert("problema ao salvar");
      }
      this.disableFormFields();
      this.setState({selectedVehicle: {}})

    })
  }

  componentDidMount() {
    this.getVehicles();
    this.cleanVehicleSelector();
  }

  formatDate(date) {
    if (date == undefined) {
      return '';
    }
    return moment(date).format('DD/MM/YYYY hh:mm:ss');
  }

  formatCurrency(value) {
    if (value === undefined) {
      return '';
    }
    return 
  }

  cleanVehicleSelector = () => {
    let i = 0;
    while (i < this.state.vehicles.length) {
      let vehicle = this.state.vehicles[i++];
      vehicle.selected = false;
    }
  }

  enableFormFieldsForCreation = () => {
    this.setState({
      isCreatingVehicle: true
    }, console.log(this.state))
  }

  disableFormFields = () => {
    this.setState({
      isEdittingVehicle: false,
      isCreatingVehicle: false
    }, console.log(this.state))
  }

  enableFormFieldsForEdition = () => {
    this.setState({
      isEdittingVehicle: true
    }, console.log(this.state))
  }

  createVehicle = () => {
    const requestInfo = {
        method: 'POST',
        body: JSON.stringify(this.state.selectedVehicle),
        credentials: "same-origin",
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        }
    };
    fetch("http://localhost:8080/vehicle", requestInfo)
    .then(Pubsub.publish(VEHICLES_UPDATED))
    .then(console.log(this.state))
  }

  updateVehicle = () => {
    const requestInfo = {
        method: 'PUT',
        body: JSON.stringify(this.state.selectedVehicle),
        credentials: "same-origin",
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        }
    };
    fetch("http://localhost:8080/vehicle/" + this.state.selectedVehicle.id, requestInfo)
    .then(Pubsub.publish(VEHICLES_UPDATED))
    .then(console.log(this.state))
  }

  selectVehicle = (row) => {
    Pubsub.publish(VEHICLE_SELECTED, this.state.vehicles[row]);
  }

  addVehicle = (event) => {
    event.preventDefault();
    Pubsub.publish(INSERT_BUTTON_CLICKED)
  }

  editVehicle = (event) => {
    event.preventDefault();
    let selectedVehicle = this.state.vehicles.filter( (v) => v.selected === true)[0];
    if (selectedVehicle === undefined) {
      alert("You must select a vehicle before");
    } else {
      Pubsub.publish(EDITION_BUTTON_CLICKED);
    }
  }

  deleteVehicle = (event) => {
    event.preventDefault();
    const requestInfo = {
        method: 'DELETE',
        credentials: "same-origin"
    };
    let selectedVehicle = this.state.vehicles.filter( (v) => v.selected === true)[0];
    if (selectedVehicle === undefined) {
      alert("You must select a vehicle before");
    } else {
      fetch('http://localhost:8080/vehicle/' + selectedVehicle.id, requestInfo)
        .then(Pubsub.publish(VEHICLE_REMOVED))
        .then(console.log(this.state))
    }
  }

  saveVehicle = (event) => {
    event.preventDefault();
    Pubsub.publish(SAVE_BUTTON_CLICKED);
  }

  fillEditionVehicle = (vehicle) => {
    console.log(vehicle);
    this.setState({
      selectedVehicle: vehicle
    })
  }

  changeBrand = (event) => {
    let vehicle = this.state.selectedVehicle;
    vehicle.brand = event.target.value;
    this.setState({ selectedVehicle: vehicle});
  }

  changeModel = (event) => {
    let vehicle = this.state.selectedVehicle;
    vehicle.model = event.target.value;
    this.setState({ selectedVehicle: vehicle});
  }

  changeColor = (event) => {
    let vehicle = this.state.selectedVehicle;
    vehicle.color = event.target.value;
    this.setState({ selectedVehicle: vehicle});
  }

  changeYear = (event) => {
    let vehicle = this.state.selectedVehicle;
    vehicle.year = event.target.value;
    this.setState({ selectedVehicle: vehicle});
  }

  changePrice = (event) => {
    let vehicle = this.state.selectedVehicle;
    vehicle.price = event.target.value;
    this.setState({ selectedVehicle: vehicle});
  }

  changeDescription = (event) => {
    let vehicle = this.state.selectedVehicle;
    vehicle.description = event.target.value;
    this.setState({ selectedVehicle: vehicle});
  }

  changeIsNew = (event) => {
    let vehicle = this.state.selectedVehicle;
    let oldValue = vehicle.new === undefined ? true : !vehicle.new;
    vehicle.new = oldValue;
    this.setState({ selectedVehicle: vehicle});
  }

  vehiclesTable = (vehicles) => (
    <Table key="vehiclesTable" onRowSelection={row => this.selectVehicle(row)}>
      <TableHeader>
        <TableRow>
          <TableHeaderColumn>Brand</TableHeaderColumn>
          <TableHeaderColumn>Model</TableHeaderColumn>
          <TableHeaderColumn>Color</TableHeaderColumn>
          <TableHeaderColumn>Year</TableHeaderColumn>
          <TableHeaderColumn>Price</TableHeaderColumn>
          <TableHeaderColumn>Description</TableHeaderColumn>
          <TableHeaderColumn>New</TableHeaderColumn>
          <TableHeaderColumn>Creation</TableHeaderColumn>
          <TableHeaderColumn>Update</TableHeaderColumn>
        </TableRow>
      </TableHeader>
      <TableBody deselectOnClickaway={false}>
        {
          vehicles.map(vehicle =>
            {
              return (
                <TableRow key={vehicle.id}>
                  <TableRowColumn>{vehicle.brand}</TableRowColumn>
                  <TableRowColumn>{vehicle.model}</TableRowColumn>
                  <TableRowColumn>{vehicle.color}</TableRowColumn>
                  <TableRowColumn>{vehicle.year}</TableRowColumn>
                  <TableRowColumn>
                    <NumberFormat thousandSeparator={true} displayType={'text'}  value={vehicle.price} prefix={'$'} />
                  </TableRowColumn>
                  <TableRowColumn>{vehicle.description}</TableRowColumn>
                  <TableRowColumn>
                    <Checkbox checked={vehicle.new}/>
                  </TableRowColumn>
                  <TableRowColumn>
                    {this.formatDate(vehicle.creation)}
                  </TableRowColumn>
                  <TableRowColumn>
                    {this.formatDate(vehicle.update)}
                  </TableRowColumn>
                </TableRow>
              )
            }
          )
        }
      </TableBody>
    </Table>
  );

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <h1 className="App-title" align="center">Vehicles</h1>
        </header>
        {
          this.vehiclesTable(this.state.vehicles)
        }
        <br/>
        <div align="center">
          <RaisedButton label="Insert" primary={true} style={styles.button} onClick={(e) => this.addVehicle(e)}/>
          <RaisedButton label="Edit" primary={true} style={styles.button} onClick={(e) => this.editVehicle(e)}/>
          <RaisedButton label="Delete" primary={true} style={styles.button} onClick={(e) => this.deleteVehicle(e)}/>
        </div>
        <br/>
        <br/>
        <div key="form" style={this.state.isCreatingVehicle || this.state.isEdittingVehicle ? styles.formShow : styles.formHide} align="center">
          <Card >
            <CardHeader
            title="Vehicle"/>
            <TextField id="inptBrand" onChange={this.changeBrand}
              floatingLabelText="Vehicle's Brand" disabled={this.state.isEdittingVehicle}
              length="40" value={this.state.selectedVehicle.brand}/><br />
            <TextField id="inptModel" hintText="Vehicle's Model" onChange={this.changeModel}
              floatingLabelText="Vehicle's Model" disabled={this.state.isEdittingVehicle}
              length="50" value={this.state.selectedVehicle.model}/><br />
            <TextField id="inptColor" hintText="Vehicle's Color"
              floatingLabelText="Vehicle's Color" onChange={this.changeColor}
              length="50" value={this.state.selectedVehicle.color}/><br />
            <TextField id="inptYear" hintText="Vehicle's Year" type="number"
              floatingLabelText="Vehicle's Year" disabled={this.state.isEdittingVehicle}
              maxlength="4" value={this.state.selectedVehicle.year} onChange={this.changeYear}/><br />
            <TextField id="inptPrice" hintText="Vehicle's Price" type="number"
              floatingLabelText="Vehicle's Price" onChange={this.changePrice}
              length="11" value={this.state.selectedVehicle.price}/><br />
            <TextField id="inptDescription" hintText="Vehicle's Description"
              floatingLabelText="Vehicle's Description" onChange={this.changeDescription}
              length="255" value={this.state.selectedVehicle.description}/><br /><br />
            <Toggle
              label="Vehicle's is new?"
              style={styles.toggle} onClick={this.changeIsNew}
              toggled={this.state.selectedVehicle.new}
            />
            <TextField id="inptCreation" hintText="Vehicle's Creation Date"
              floatingLabelText="Vehicle's Creation Date" disabled={true}
              length="50" value={this.formatDate(this.state.selectedVehicle.creation)}/><br />
            <TextField id="inptUpdate" hintText="Vehicle's Update Date"
              floatingLabelText="Vehicle's Update Date" disabled={true}
              length="50" value={this.formatDate(this.state.selectedVehicle.update)}/><br />
            <RaisedButton label="Save" primary={true} style={styles.button} onClick={(e) => this.saveVehicle(e)}/>
            
          </Card>
        </div>
      </div>
    );
  }
}
