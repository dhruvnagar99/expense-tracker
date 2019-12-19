import React, { Component } from 'react';
import AppNav from './AppNav';
import {Container} from 'reactstrap';



class Home extends Component {

    
    render() { 

        return (
            <Container>
            <div className="container">
            <AppNav/>
            </div>
            <h2>Welcome  to Expense Tracker Application</h2>
            <h4>Please <span><a href='/signin'>sign in</a></span> to view your account</h4>
          </Container>
        );
      }
    }
  

 
export default Home;