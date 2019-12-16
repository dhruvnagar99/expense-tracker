import React, { Component } from 'react';
import Category from './Category';
import Home from './Home';
import Expenses from './Expenses';
import { Login, Register } from "./components/login/index";

import {BrowserRouter as Router, Route, Switch, withRouter} from 'react-router-dom';
import './App.scss';

function App() {
  return (
    <Router>
      <Switch>
        <Route path = '/' exact = {true} component = {Home} /> 
        <Route path = '/categories' exact = {true} component = {Category} />
        <Route path = '/expenses' exact = {true} component = {Expenses} />
      </Switch>
    </Router>
  );
}

export default withRouter(App);
