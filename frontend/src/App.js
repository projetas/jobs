import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import MyVehicles from './MyVehicles';

class App extends Component {
  render() {
    return (
      <MuiThemeProvider>
        <MyVehicles />
      </MuiThemeProvider>
    );
  }
}

export default App;