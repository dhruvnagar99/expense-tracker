import React, { Component } from 'react';
import {
    Navbar,
    NavbarBrand,
    Nav,
    NavItem,
    NavLink,
  } from 'reactstrap';



  class AppNav extends Component {
      state = {  }
      render() { 
        return (
            <div>
              <Navbar color="light" light expand="md">
                <NavbarBrand href="/">Expense Tracking Application</NavbarBrand>
               
                
                  <Nav className="mr-auto" navbar>
                    <NavItem>
                      <NavLink href="/">Home</NavLink>
                    </NavItem>
                    <NavItem>
                      <NavLink href="/categories">Categories</NavLink>
                    </NavItem>
                    <NavItem>
                      <NavLink href="/expenses">Expenses</NavLink>
                    </NavItem> 
                    
                    <NavItem>
                      <NavLink href="/signin">My Account</NavLink>
                    </NavItem>
                   
                  </Nav>
                  
               
              </Navbar>
            </div>
          );
      }
  }
   
  export default AppNav;