import React, { Component } from 'react';
import AppNav from './AppNav';
import Datepicker from 'react-datepicker';
import "react-datepicker/dist/react-datepicker.css";
import './App.scss';
import Moment from 'react-moment';
import {Input, Label, Table, Container, Form, FormGroup, Button} from 'reactstrap';
import {Link} from 'react-router-dom';
import Category from './Category';


class Expenses extends Component {

emptyItem = {
    id: 0,
    expensedate: new Date(),
    descript: '',
    location: '',
    category: {
        id : 0
    },
    user: {
        id : 1
    }
}

    state = { 
        date: new Date(),
        isLoading: true,
        expense: [],
        Categories: [],
        Expenses: [],
        item: this.emptyItem
     }

     constructor(props){
         super(props)
         this.state = {
             isLoading : false,
             Categories : [],
             date : new Date(),
             Expenses: [],
             item: this.emptyItem 
         }
         this.handleSubmit = this.handleSubmit.bind(this);
         this.handleChange = this.handleChange.bind(this);
         this.handleDateChange = this.handleDateChange.bind(this);
         this.handleDropdownChange = this.handleDropdownChange.bind(this);

     }

     async componentDidMount(){
            const response = await fetch('/api/categories');
            const body = await response.json();
            this.setState({Categories : body, isLoading : false});

            const responseExp = await fetch('/api/expenses');
            const bodyExp = await responseExp.json();
            this.setState({Expenses : bodyExp, isLoading : false});
     }


     async handleSubmit(event){
         event.preventDefault();
         
       
         const item = this.state.item;

         await fetch (`/api/expenses`, {
             method: 'POST',
             headers :{
                 'Accept' : 'application/json',
                 'Content-Type' : 'application/json'
             },
             body : JSON.stringify(item)
         });
         console.log(this.state);
         this.props.history.push("/expenses");
     }

     handleChange(event){
         const target = event.target;
         const value = target.value;
         const name = target.name;
         let item ={...this.state.item};
         item[name] = value;
         this.setState({item});
         console.log(this.state);
     }

     handleDateChange(date){
         let item = {...this.state.item};
         item.expensedate = date;
         this.setState({item});

     }
     handleDropdownChange(e){
        let item = {...this.state.item};
        item.category.id = e.target.value;
        this.setState({item});

        console.log(this.state);

     }

     async remove(id){
         await fetch(`api/expense/${id}`, {
             method: 'DELETE',
             headers: {
                 'Accept': 'application/json',
                 'Content-Type': 'application/json'
             }
         }).then(()=>{
             let updatedExpenses = [...this.state.Expenses].filter(i=> i.id !== id);
             this.setState({Expenses : updatedExpenses});

         });
     }

    render() { 
        const title = <h2>Add Expense</h2>
        const {Categories} = this.state;
        const {Expenses, isLoading} = this.state;

        let optionList =  
            Categories.map(category=> 
             <option value={category.id} key={category.id} >
                {category.name}
             </option>
            );
           
        let rows=
                Expenses.map(expense =>
                    <tr key={expense.id}>
                        <td>{expense.descript}</td>
                        <td>{expense.category.name}</td>
                        <td>{expense.location}</td>
                        <td><Moment date = {expense.expensedate} format = "YYYY-MM-DD"></Moment></td>
                        <td><Button size="sm" color="danger" onClick={() => this.remove(expense.id)}>Delete</Button></td>
                        
                    </tr>)

        if(isLoading)
            return (<div>Loading ...</div>)
        return ( 
        <div>
            <AppNav/>
       <Container>
           {title}
       <Form onSubmit = {this.handleSubmit}>
           <FormGroup>
               <Label for = "descript">Description </Label>
               <Input type="text" name="descript" id="descript" onChange={this.handleChange} />
           </FormGroup>

           <FormGroup>
               <Label for = "category">Category</Label>
               <select value={this.state.item.category.id} onChange={this.handleDropdownChange}>
               <option value='0' disabled>Select</option>
                   {optionList}
               </select>
           </FormGroup>
           
           <FormGroup>
               <Label for = "expenseDate">Epense Date</Label>
               <Datepicker selected={this.state.item.expensedate} onChange={this.handleDateChange} />
           </FormGroup>
           <FormGroup>
               <Label for = "location">Location</Label>
               <Input type="text" name="location" id="location" onChange={this.handleChange} />
           </FormGroup>
           <FormGroup>
               <Button color = "primary" type="submit">Save</Button>{''}
               <Button color = "secondary" tag ={Link} to = "/categories">Cancel</Button>

           </FormGroup>
       </Form>

       </Container>
       {''}

       <Container>
           <h3>Expense List</h3>
           <Table className = "mt-4">
               <thead>
                   <tr>
                       <th width ="30%">Description</th>
                       <th width ="20%">Category</th>
                       <th width ="20%">Location</th>   
                       <th width ="20%">Date</th>   
                       <th width ="20%">Action</th>   
                   </tr>
               </thead>
               <tbody>
                   {rows}
               </tbody>

           </Table>
       </Container>
        </div>
         );
    }
}
 
export default Expenses;