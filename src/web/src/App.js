import React, { Component } from 'react';
import './App.css';
import axios from 'axios';
import Menu from './Menu';

class App extends Component {

 constructor () {
    super()
     this.state = {
          id:'',
          model: '',
          brand: '',
          color: '',
          year : '',
          price: '',
          isNew: '',
          createDate: '',
          lastUpdate: '',
          description: '',
        }

  }


  render () {
    return (
      <div className='button__container'>
           <Menu />
      </div>
    )
  }
}

export default App;
