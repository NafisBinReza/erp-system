import React,{useState} from 'react'
import "../../nav.css"
import axios from 'axios';
import base_url from '../api'
import { ToastContainer, toast } from 'react-toastify';
import {Link, useHistory} from "react-router-dom";
import {Button} from "react-bootstrap";
import "../../custom.css";

export default function AddRecruitment() {

    const notify = () => toast.success("Recruitment added successfully", {autoClose:2000});
    const history = useHistory();
    const [name,setName]=useState('');
    const [creationDate,setCreationDate]=useState('');
    const [subject,setSubject]=useState('');
    const [email,setEmail]=useState('');
    const [phone,setPhone]=useState('');
    const [mobile,setMobile]=useState('');
    const [degree,setDegree]=useState('');
    const [jobTitle,setJobTitle]=useState('');
    const [department,setDepartment]=useState('');
    const [stage,setStage]=useState('');
    const [medium,setMedium]=useState('');
    const [source,setSource]=useState('');
    const [availability,setAvailability]=useState('');
    const [appreciation,setAppreciation]=useState('');
    const [responsible,setResponsible]=useState('');
    const [expectedSalary,setExpectedSalary]=useState('');
    const [proposedSalary,setProposedSalary]=useState('');


    async function tagSubmit(e){
            e.preventDefault();
            const data = {
                'name':name,
                'creationDate':creationDate,
                'subject':subject,
                'email':email,
                'phone':phone,
                'mobile': mobile,
                'department': department,
                'jobTitle':jobTitle,
                'stage': stage,
                'medium': medium,
                'source': source,
                'appreciation': appreciation,
                'responsible': responsible,
                'expectedSalary': expectedSalary,
                'degree': degree,
                'proposedSalary': proposedSalary,
                'availability': availability
            };

            axios.post(`${base_url}/recruitment/rec`, data)
          .then(async (response) => {
              notify();
              await new Promise(resolve => setTimeout(resolve, 2000));
              history.push("/recruitment");
          }).catch((err) => {
            console.log( err.data );
          });
        }
    return (
        <div className='py-5'>
        <h2 className="text-center">Add Recruitment</h2>
        <ToastContainer />
        <form onSubmit={tagSubmit}>
        {/*<label for="fname" style={{ fontSize:"20px"}}>Id</label>*/}
        {/*<input type="text" id="fname" name="id" value={id} onChange={(e)=>setId(e.target.value)} />*/}
            <div className="d-flex align-items-center">
                <label htmlFor="name" style={{fontSize: "15px", margin:"0 20px"}}>Name</label>
                <input type="text" id="name" name="name" value={name} onChange={(e) => setName(e.target.value)}/>
                <label htmlFor="subject" style={{fontSize: "15px", margin:"0 20px"}}>Subject/Application Name</label>
                <input type="text" id="subject" name="subject" value={subject}
                      onChange={(e) => setSubject(e.target.value)}/>
            </div>
            <div className="d-flex align-items-center">
                <label htmlFor="creationDate" style={{fontSize: "15px", margin:"0 20px"}}>Date</label>
                <input type="date" id="creationDate" name="creationDate" value={creationDate}
                       onChange={(e) => setCreationDate(e.target.value)}/>
                <label htmlFor="email" style={{fontSize: "15px", margin:"0 20px"}}>Email</label>
                <input type="text" id="email" name="email" value={email}
                   onChange={(e) => setEmail(e.target.value)}/>
            </div>
            <div className="d-flex align-items-center">
                <label htmlFor="phone" style={{fontSize: "15px", margin:"0 20px"}}>Phone</label>
                <input type="text" id="phone" name="phone" value={phone} onChange={(e) => setPhone(e.target.value)}/>
                <label htmlFor="mobile" style={{fontSize: "15px", margin:"0 20px"}}>Mobile</label>
                <input type="text" id="mobile" name="mobile" value={mobile}
                       onChange={(e) => setMobile(e.target.value)}/>
            </div>
            <div className="d-flex align-items-center">
                <label htmlFor="degree" style={{fontSize: "15px", margin:"0 20px"}}>Degree</label>
                <input type="text" id="degree" name="degree" value={degree} onChange={(e) => setDegree(e.target.value)}/>
                <label htmlFor="responsible" style={{fontSize: "15px", margin:"0 20px"}}>Responsible</label>
                <input type="text" id="responsible" name="responsible" value={responsible}
                       onChange={(e) => setResponsible(e.target.value)}/>
            </div>
            <div className="d-flex align-items-center">
                <label htmlFor="appreciation" style={{fontSize: "15px", margin:"0 20px"}}>Appreciation</label>
                <input type="text" id="appreciation" name="appreciation" value={appreciation} onChange={(e) => setAppreciation(e.target.value)}/>
                <label htmlFor="source" style={{fontSize: "15px", margin:"0 20px"}}>Source</label>
                <input type="text" id="source" name="source" value={source}
                       onChange={(e) => setSource(e.target.value)}/>
            </div>

            <h3>Job Details</h3>
            <div className="d-flex align-items-center">
                <label htmlFor="jobTitle" style={{fontSize: "15px", margin:"0 20px"}}>Applied Job</label>
                <input type="text" id="jobtitle" name="jobTitle" value={jobTitle} onChange={(e) => setJobTitle(e.target.value)}/>
                <label htmlFor="department" style={{fontSize: "15px", margin:"0 20px"}}>Department</label>
                <input type="text" id="department" name="department" value={department}
                       onChange={(e) => setDepartment(e.target.value)}/>
            </div>
            <div className="d-flex align-items-center">
                <label htmlFor="expectedSalary" style={{fontSize: "15px", margin:"0 20px"}}>Expected Salary</label>
                <input type="text" id="expectedSalary" name="expectedSalary" value={expectedSalary} onChange={(e) => setExpectedSalary(e.target.value)}/>
                <label htmlFor="proposedSalary" style={{fontSize: "15px", margin:"0 20px"}}>Proposed Salary</label>
                <input type="text" id="proposedSalary" name="proposedSalary" value={proposedSalary}
                       onChange={(e) => setProposedSalary(e.target.value)}/>
            </div>
            <div className="d-flex align-items-center">
                <label htmlFor="stage" style={{fontSize: "15px", margin:"0 20px"}}>Stage</label>
                <input type="text" id="stage" name="stage" value={stage} onChange={(e) => setStage(e.target.value)}/>
                <label htmlFor="availability" style={{fontSize: "15px", margin:"0 20px"}}>Availability</label>
                <input type="text" id="availability" name="availability" value={availability}
                       onChange={(e) => setAvailability(e.target.value)}/>
                <label htmlFor="medium" style={{fontSize: "15px", margin:"0 20px"}}>Medium</label>
                <input type="text" id="medium" name="medium" value={medium} onChange={(e) => setMedium(e.target.value)}/>
            </div>

        <input type="submit" value="Submit" style={{ backgroundColor:"blue",fontSize:"22px", borderRadius:"10px", padding:"10px", color:"white"}}/>
            <Button type="button" className="m-3">
                <Link to={"/recruitment"} style={{color: "white", textDecoration: "none"}}>Cancel</Link></Button>
        </form>
    </div>
    )
}
