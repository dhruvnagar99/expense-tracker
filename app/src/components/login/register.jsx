import React from "react";
import regImg from "../../reg.svg";
import {Input, Label, Table, Container, Form, FormGroup, Button} from 'reactstrap';
import { Route , withRouter, Redirect} from 'react-router-dom';
import App from '../../App';
export class Register extends React.Component {

    emptyItem = {
        id: 0,
        createddate: new Date(),
        email: '',
        firstName: '',
        lastName: '',
        password: '',
        ssoId: '',
        State: 'Active'
    }

    state = { 
        date: new Date(),
        isLoading: true,
        Users: [],
        toExpenses : false,
        item: this.emptyItem
     }
     constructor(props) {
        super(props);
        this.state = {
            isLoading : false,
             Categories : [],
             date : new Date(),
             Expenses: [],
             item: this.emptyItem 
         };
         this.handleSubmit = this.handleSubmit.bind(this);
         this.handleSSOChange = this.handleSSOChange.bind(this);
         this.handlefirstNameChange = this.handlefirstNameChange.bind(this);
         this.handlelastNameChange = this.handlelastNameChange.bind(this);
         this.handleEmailChange = this.handleEmailChange.bind(this);
         this.handlePasswordChange = this.handlePasswordChange.bind(this);
      }
      

     async componentDidMount(){
        const response = await fetch('/api/users');
        const body = await response.json();
        this.setState({Users : body, isLoading : false});
 }

 async handleSubmit(event){
    event.preventDefault();
  
    const item = this.state.item;

    await fetch (`/api/newUser`, {
        method: 'POST',
        headers :{
            'Accept' : 'application/json',
            'Content-Type' : 'application/json'
        },
        body : JSON.stringify(item)
    })
    .then(() => this.setState(() => ({
        toExpenses : true
    })));
    console.log(this.state);
    
}

handleSSOChange(event){
    const target = event.target;
    const value = target.value;
    const ssoId = target.name;
    let item ={...this.state.item};
    item[ssoId] = value;
    this.setState({item});
    console.log(this.state);
}

handlefirstNameChange(event){
    const target = event.target;
    const value = target.value;
    const firstName = target.name;
    let item ={...this.state.item};
    item[firstName] = value;
    this.setState({item});
    console.log(this.state);
}

handlelastNameChange(event){
    const target = event.target;
    const value = target.value;
    const lastName = target.name;
    let item ={...this.state.item};
    item[lastName] = value;
    this.setState({item});
    console.log(this.state);
}
handleEmailChange(event){
    const target = event.target;
    const value = target.value;
    const name = target.name;
    let item ={...this.state.item};
    item[name] = value;
    this.setState({item});
    console.log(this.state);
}
handlePasswordChange(event){
    const target = event.target;
    const value = target.value;
    const password = target.name;
    let item ={...this.state.item};
    item[password] = value;
    this.setState({item});
    console.log(this.state);
}
  

  render() {
    if(this.state.toExpenses ===true){
       return <Redirect to = '/expenses' />
    }
    return (
      <div className="base-container" ref={this.props.containerRef}>
       
        <div className="content">
          
          <div className="form">
          <Form onSubmit = {this.handleSubmit}>
            <div className="form-group-register">
            <FormGroup>
              <Label for="name">Username</Label>
              <Input type="text" name="ssoId" id="ssoId" placeholder="username" onChange={this.handleSSOChange} />
            </FormGroup>
            </div>

            <div className="form-group-register">
            <FormGroup>
              <Label for="firstName">First Name</Label>
              <Input type="text" name="firstName" id="firstName" placeholder="first name" onChange={this.handlefirstNameChange} />
            </FormGroup> 
            </div>

            <div className="form-group-register">
            <FormGroup>
              <Label for="lastName">Last Name</Label>
              <Input type="text" name="lastName" id="lastName" placeholder="last name" onChange={this.handlelastNameChange} />
            </FormGroup>
            </div>


            <div className="form-group-register">
            <FormGroup>
              <Label htmlFor="email">Email</Label>
              <Input type="text"  name="email" id="email" placeholder="email" onChange={this.handleEmailChange} />
              </FormGroup>
            </div>
            <div className="form-group-register">
            <FormGroup>
              <Label htmlFor="password">Password</Label>
              <Input type="text" name="password" id="password" placeholder="password" onChange={this.handlePasswordChange} />
            </FormGroup>
            </div>
            <div className="footer">
        <FormGroup>
          <Button type="submit" className="btn">
            Register
          </Button>
          </FormGroup>
        </div>
            </Form>
          </div>
        </div>
       
      </div>
    );
  }
}
