import React, { Component } from 'react';
import AppNav from './AppNav';
import {Container} from 'reactstrap';
import { Login, Register } from "./components/login/index";



class Signin extends Component {

    emptyItem = {
        id: 0,
        createddate: new Date(),
        name: '',
        email: '',
    }

    state = { 
        date: new Date(),
        isLoading: true,
        users: [],
        item: this.emptyItem
     }


    constructor(props){
        super(props)
         this.state = {
            isLogginActive: true,
            isLoading : false,
             Categories : [],
             date : new Date(),
             Expenses: [],
             item: this.emptyItem 
         };
    }

   async componentDidMount() {
        //Add .right by default
        this.rightSide.classList.add("right");
      }

      changeState() {
        const { isLogginActive } = this.state;
    
        if (isLogginActive) {
          this.rightSide.classList.remove("right");
          this.rightSide.classList.add("left");
        } else {
          this.rightSide.classList.remove("left");
          this.rightSide.classList.add("right");
        }
        this.setState(prevState => ({ isLogginActive: !prevState.isLogginActive }));
      }
    
    render() { 

        const { isLogginActive } = this.state;
        const current = isLogginActive ? "Register" : "Login";
        const currentActive = isLogginActive ? "login" : "register";
        return (
            <Container>
            <div className="container">
            <AppNav/>
            </div>
            

          <div className="App">
            <div className="login">
              <div className="container" ref={ref => (this.container = ref)}>
                {isLogginActive && (
                  <Login containerRef={ref => (this.current = ref)} />
                )}
                {!isLogginActive && (
                  <Register containerRef={ref => (this.current = ref)} />
                )}
              </div>
              <RightSide
                current={current}
                currentActive={currentActive}
                containerRef={ref => (this.rightSide = ref)}
                onClick={this.changeState.bind(this)}
              />
            </div>
          </div>
          </Container>
        );
      }
    }
    const RightSide = props => {
        return ( 
           

<div
      className="right-side"
      ref={props.containerRef}
      onClick={props.onClick}
    >
      <div className="inner-container">
        <div className="text">{props.current}</div>
      </div>
    </div>

   
         );
    };

 
export default Signin;