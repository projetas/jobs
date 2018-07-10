import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import axios from 'axios';
import Button from '@material-ui/core/Button';


const CustomTableCell = withStyles(theme => ({
  head: {
    backgroundColor: theme.palette.common.black,
    color: theme.palette.common.white,
  },
  body: {
    fontSize: 14,
  },
}))(TableCell);


const styles = theme => ({
  root: {
    width: '100%',
    marginTop: theme.spacing.unit * 3,
    overflowX: 'auto',
  },
  table: {
    minWidth: '100%',
  },
  row: {
    '&:nth-of-type(odd)': {
      backgroundColor: theme.palette.background.default,
    },
  },
});

var data = [];
function handleClick () {
    axios.get('http://localhost:8080/api/cars').then(response => { data = response.data; } )
  }


function CustomizedTable(props) {


  const { classes } = props;

  //Load car
  handleClick();

  return (
    <Paper className={classes.root}>
      <Table className={classes.table}>
        <TableHead>
          <TableRow>
            <CustomTableCell>ID</CustomTableCell>
            <CustomTableCell>Brand</CustomTableCell>
            <CustomTableCell>Model</CustomTableCell>
            <CustomTableCell>Color</CustomTableCell>
            <CustomTableCell>Year</CustomTableCell>
            <CustomTableCell>Price</CustomTableCell>
            <CustomTableCell>Description</CustomTableCell>
            <CustomTableCell>Is New?</CustomTableCell>
            <CustomTableCell>Created</CustomTableCell>
            <CustomTableCell>LastUpdate</CustomTableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {data.map(n => {
            return (
              <TableRow className={classes.row} key={n.id}>
                <CustomTableCell>{n.id}</CustomTableCell>
                <CustomTableCell>{n.brand}</CustomTableCell>
                <CustomTableCell>{n.model}</CustomTableCell>
                <CustomTableCell>{n.color}</CustomTableCell>
                <CustomTableCell>{n.year}</CustomTableCell>
                <CustomTableCell>{n.price}</CustomTableCell>
                <CustomTableCell>{n.description = '' ? 'No description' : n.description }</CustomTableCell>
                <CustomTableCell>{n.isNew = true ? 'Yes' : 'No'}</CustomTableCell>
                <CustomTableCell>{n.createDate}</CustomTableCell>
                <CustomTableCell>{n.lastUpdate}</CustomTableCell>
              </TableRow>
            );
          })}
        </TableBody>
      </Table>
    </Paper>
  );
}

CustomizedTable.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(CustomizedTable);
