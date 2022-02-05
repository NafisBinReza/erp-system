import './App.css';
import Header from './components/Header';
import { ToastContainer, toast } from 'react-toastify';
import Home from './components/Home';
import Nav from './components/Nav';
import Employee from './components/Employee/Employee';
import Leave from './components/Leave/Leave';
import Footer from './components/Footer';
import AddEmployee from './components/Employee/AddEmployee';
import EditEmployee from './components/Employee/EditEmployee';
import About from './components/About';
import {
  Switch,
  Route
} from "react-router-dom";
import EmployeeInfo from "./components/Employee/EmployeeInfo";
import AddLeave from "./components/Leave/AddLeave";
import LeaveInfo from "./components/Leave/LeaveInfo";
import EditLeave from "./components/Leave/EditLeave";
import StatLeave from "./components/Leave/StatLeave";
import Recruitment from "./components/Recruitment/Recruitment";
import AddRecruitment from "./components/Recruitment/AddRecruitment";
import RecruitmentInfo from "./components/Recruitment/RecruitmentInfo";
import EditRecruitment from "./components/Recruitment/EditRecruitment";
import Jobs from "./components/Recruitment/Jobs";
import Customer from "./components/CRM/Customer";
import AddCustomer from "./components/CRM/AddCustomer";
import CustomerInfo from "./components/CRM/CustomerInfo";
import EditCustomer from "./components/CRM/EditCustomer";

function App() {
  return (
    <>
    <Nav />
    <Switch>
              <div class="container">
              <Route exact path="/" >
                <Home />
              </Route>


              {/*Employee Module Routes*/}
              <Route exact path="/employee" >
                <Employee />
              </Route>
              <Route exact path="/addEmployee">
                <AddEmployee />
              </Route>
                  <Route exact path="/employeeInfo/:id">
                      <EmployeeInfo/>
                  </Route>
              <Route exact path="/editEmployee/:id">
                <EditEmployee />
              </Route>


                  {/*Leave Module Routes*/}
                  <Route exact path="/leave" >
                      <Leave/>
                  </Route>
                  <Route exact path="/addLeave" >
                      <AddLeave/>
                  </Route>
                  <Route exact path="/leaveInfo/:id">
                      <LeaveInfo/>
                  </Route>
                  <Route exact path="/editLeave/:id">
                      <EditLeave/>
                  </Route>


                  {/*Recruitment Module Routes*/}
                  <Route exact path="/recruitment" >
                      <Recruitment/>
                  </Route>
                  <Route exact path="/addRecruitment" >
                      <AddRecruitment/>
                  </Route>
                  <Route exact path="/recruitmentInfo/:id">
                      <RecruitmentInfo/>
                  </Route>
                  <Route exact path="/editRecruitment/:id">
                      <EditRecruitment/>
                  </Route>
                  <Route exact path="/jobs">
                      <Jobs/>
                  </Route>

                  {/*CRM Module Routes*/}
                  <Route exact path="/customer">
                      <Customer/>
                  </Route>
                  <Route exact path="/addCustomer">
                      <AddCustomer/>
                  </Route>
                  <Route exact path="/editCustomer/:id">
                      <EditCustomer/>
                  </Route>
                  <Route exact path="/customerInfo/:id">
                      <CustomerInfo/>
                  </Route>



                  <Route exact path="/about">
                  <About/>
              </Route>
              </div>
              
          </Switch>
      {/*<Footer />*/}
</>
  );
}

export default App;
