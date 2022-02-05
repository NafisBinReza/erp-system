import React,{useState} from 'react'
import "../../nav.css"
import axios from 'axios';
import base_url from '../api'
import { ToastContainer, toast } from 'react-toastify';
import {Link, useHistory} from "react-router-dom";
import {Button} from "react-bootstrap";
import "../../custom.css";

export default function AddEmployee() {
    const notify = () => toast.success("Employee added successfully", {autoClose:2000});
    const history = useHistory();
    const [name,setName]=useState('');
    const [waddress,setWaddress]=useState('');
    const [wmobile,setWmobile]=useState('');
    const [wemail,setWemail]=useState('');
    const [wlocation,setWlocation]=useState('');
    const [wphone,setWphone]=useState('');
    const [password,setPassword]=useState('');
    const [roles,setRoles]=useState('');
    const [department,setDepartment]=useState('');
    const [jobTitle,setJobTitle]=useState('');
    const [manager,setManager]=useState('');
    const [coach,setCoach]=useState('');
    const [nationality,setNationality]=useState('');
    const [identificationNo,setIdentificationNo]=useState('');
    const [passport,setPassport]=useState('');
    const [bankAccountNo,setBankAccountNo]=useState('');
    const [gender,setGender]=useState('');
    const [maritalStatus,setMaritalStatus]=useState('');
    const [homeAddress,setHomeAddress]=useState('');
    const [dateOfBirth,setDateOfBirth]=useState('');

    async function tagSubmit(e){
        const regex =
            /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
        if((name!="" || wemail!="") && regex.test(wemail)){
            e.preventDefault();
            const data = {
                'username':name,
                'workingAddress':waddress,
                'workMobile':wmobile,
                'workEmail':wemail,
                'workLocation':wlocation,
                'workPhone':wphone,
                'gender': gender,
                'password': password,
                'roles': roles,
                'department': department,
                'jobTitle': jobTitle,
                'manager': manager,
                'coach': coach,
                'nationality': nationality,
                'identificationNo': identificationNo,
                'passport': passport,
                'bankAccountNo': bankAccountNo,
                'maritalStatus': maritalStatus,
                'homeAddress': homeAddress,
                'dateOfBirth': dateOfBirth
            };

            axios.post(`${base_url}/employees/emp`, data)
          .then(async (response) => {
              setName("");
              setWemail("");
              setWmobile("");
              setWaddress("");
              setWlocation("");
              setWphone("");
              notify();
              await new Promise(resolve => setTimeout(resolve, 2000));
              history.push("/employee");
          }).catch((err) => {
            console.log( err.data );
          });
        }
        else{
            alert("all fields are required or put a valid email address");
        }
    }
    return (
        <div className='py-5'>
            <h2 className="text-center">Add Employee</h2>
            <ToastContainer />
            <form onSubmit={tagSubmit}>
            {/*<label for="fname" style={{ fontSize:"20px"}}>Id</label>*/}
            {/*<input type="text" id="fname" name="id" value={id} onChange={(e)=>setId(e.target.value)} />*/}
                <div className="d-flex align-items-center">
                    <label htmlFor="name" style={{fontSize: "15px", margin:"0 20px"}}>Name</label>
                    <input type="text" id="name" name="name" value={name} onChange={(e) => setName(e.target.value)}/>
                    <label htmlFor="password" style={{fontSize: "15px", margin:"0 20px"}}>Password</label>
                    <input type="text" id="password" name="password" value={password}
                           onChange={(e) => setPassword(e.target.value)}/>
                    <label htmlFor="roles" style={{fontSize: "15px", margin:"0 20px"}}>Roles</label>
                    <input type="text" id="roles" name="roles" value={roles}
                           onChange={(e) => setRoles(e.target.value)}/>
                </div>

                <h3>Contact Information</h3>
                <div className="d-flex align-items-center">
                    <label htmlFor="workAddress" style={{fontSize: "15px", margin:"0 20px"}}>Work Address</label>
                    <input type="text" id="workAddress" name="waddress" value={waddress}
                           onChange={(e) => setWaddress(e.target.value)}/>
                    <label htmlFor="workEmail" style={{fontSize: "15px", margin:"0 20px"}}>Work Email</label>
                    <input type="text" id="workEmail" name="wemail" value={wemail} onChange={(e) => setWemail(e.target.value)}/>
                    <label htmlFor="workLocation" style={{fontSize: "15px", margin:"0 20px"}}>Work Location</label>
                    <input type="text" id="workLocation" name="wlocation" value={wlocation}
                           onChange={(e) => setWlocation(e.target.value)}/>
                </div>

                <div className="d-flex align-items-center">
                    <label htmlFor="workMobile" style={{fontSize: "15px", margin:"0 20px"}}>Work Mobile</label>
                    <input type="text" id="workMobile" name="wmobile" value={wmobile}
                           onChange={(e) => setWmobile(e.target.value)}/>
                    <label htmlFor="workPhone" style={{fontSize: "15px", margin:"0 20px"}}>Work Phone</label>
                    <input type="text" id="workPhone" name="wphone" value={wphone}
                           onChange={(e) => setWphone(e.target.value)}/>
                </div>

                <h3>Position</h3>
                <div className="d-flex align-items-center">

                </div>

                <div className="d-flex align-items-center">
                    <label htmlFor="jobTitle" style={{fontSize: "15px", margin:"0 20px"}}>Job Title</label>
                    <input type="text" id="jobTitle" name="jobTitle" value={jobTitle}
                           onChange={(e) => setJobTitle(e.target.value)}/>
                    <label htmlFor="manager" style={{fontSize: "15px", margin:"0 20px"}}>Manager</label>
                    <input type="text" id="manager" name="manager" value={manager}
                           onChange={(e) => setManager(e.target.value)}/>

                </div>

                <div className="d-flex align-items-center">

                    <label htmlFor="department" style={{fontSize: "15px", margin:"0 20px"}}>Department</label>
                    <input type="text" id="department" name="department" value={department}
                           onChange={(e) => setDepartment(e.target.value)}/>
                    <label htmlFor="coach" style={{fontSize: "15px", margin:"0 20px"}}>Coach</label>
                    <input type="text" id="coach" name="coach" value={coach}
                           onChange={(e) => setCoach(e.target.value)}/>
                </div>

                <h3>Citizenship and Other Information</h3>
                <div className="d-flex align-items-center">

                    <label htmlFor="nationality" style={{fontSize: "15px", margin:"0 20px"}}>Nationality</label>
                    <input type="text" id="nationality" name="nationality" value={nationality}
                           onChange={(e) => setNationality(e.target.value)}/>
                    <label htmlFor="identificationNo" style={{fontSize: "15px", margin:"0 20px"}}>Identification No</label>
                    <input type="text" id="identificationNo" name="identificationNo" value={identificationNo}
                           onChange={(e) => setIdentificationNo(e.target.value)}/>
                </div>
                <div className="d-flex align-items-center">
                    <label htmlFor="bankAccountNo" style={{fontSize: "15px", margin:"0 20px"}}>Bank Account No</label>
                    <input type="text" id="bankAccountNo" name="bankAccountNo" value={bankAccountNo}
                           onChange={(e) => setBankAccountNo(e.target.value)}/>
                    <label htmlFor="passport" style={{fontSize: "15px", margin:"0 20px"}}>Passport No</label>
                    <input type="text" id="passport" name="passport" value={passport}
                           onChange={(e) => setPassport(e.target.value)}/>
                </div>

                <h3>Status</h3>
                <div className="d-flex align-items-center">
                    <label htmlFor="gender" style={{fontSize: "15px", margin:"0 20px"}}>Gender</label>
                    <input type="text" id="gender" name="gender" value={gender}
                           onChange={(e) => setGender(e.target.value)}/>
                    <label htmlFor="maritalStatus" style={{fontSize: "15px", margin:"0 20px"}}>Marital Status</label>
                    <input type="text" id="maritalStatus" name="maritalStatus" value={maritalStatus}
                           onChange={(e) => setMaritalStatus(e.target.value)}/>
                </div>
                <h3>Contact Info</h3>
                <div className="d-flex align-items-center">
                    <label htmlFor="homeAddress" style={{fontSize: "15px", margin:"0 20px"}}>Home Address</label>
                    <input type="text" id="homeAddress" name="homeAddress" value={homeAddress}
                           onChange={(e) => setHomeAddress(e.target.value)}/>
                </div>

                <h3>Birth</h3>
                <div className="d-flex align-items-center">
                    <label htmlFor="dateOfBirth" style={{fontSize: "15px", margin:"0 20px"}}>Date of Birth</label>
                    <input type="text" id="dateOfBirth" name="dateOfBirth" value={dateOfBirth}
                           onChange={(e) => setDateOfBirth(e.target.value)}/>
                </div>


            <input type="submit" value="Submit" style={{ backgroundColor:"blue",fontSize:"22px", borderRadius:"10px", padding:"10px", color:"white"}}/>
                <Button type="button" className="m-3">
                    <Link to={"/employee"} style={{color: "white", textDecoration: "none"}}>Cancel</Link></Button>
            </form>
        </div>
    )
}
