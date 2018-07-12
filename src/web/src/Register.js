import React, { Component } from 'react';
import axios from 'axios';

class DeploymentForm extends Component {
  constructor() {
    super();
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleSubmit(event) {

    event.preventDefault();
    const data = new FormData(event.target);

    if (data.get('brand') === '') {
        alert('brand cannot be null')
    } else if (data.get('model') === '') {
        alert('model cannot be null')
    } else if (data.get('color') === '') {
         alert('color cannot be null')
    } else if (data.get('year') === '') {
        alert('year cannot be null')
    } else if (data.get('price') === '') {
        alert('price cannot be null')
    } else if (data.get('isNew') === '') {
        alert('isNew cannot be null')
    }
     else { axios.post('http://localhost:8080/api/car/', { "brand": data.get('brand'),
                                                   "model": data.get('model'),
                                                   "color": data.get('color'),
                                                   "year":	data.get('year'),
                                                   "price": data.get('price'),
                                                   "isNew": data.get('isNew'),
                                                   "description": data.get('description')})
            .then(res => { alert("Done!"); window.location.reload();
            })
      }
  }

  render() {
    return (
      <form onSubmit={this.handleSubmit}>
        <label htmlFor="brand">Brand</label>
        <input id="brand" name="brand" type="text" />
        <br/>
        <label htmlFor="model">Model</label>
        <input id="model" name="model" type="text" />
        <br/>
        <label htmlFor="color">Color</label>
        <input id="color" name="color" type="text" />
        <br/>
        <label htmlFor="year">Year</label>
        <input id="year" name="year" type="text" />
        <br/>
        <label htmlFor="price">Price</label>
        <input id="price" name="price" type="text" />
        <br/>
        <label htmlFor="isNew">Is a new Car?</label>
        <input id="isNew" name="isNew" type="text" />
        <br/>
        <label htmlFor="description">Description</label>
        <input id="description" name="description" type="text" />
        <br/>
        <button>Register</button>
      </form>
    );
  }
}

export default DeploymentForm;
