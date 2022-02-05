import React,{useState} from 'react'
import "../../nav.css"
import axios from 'axios';
import base_url from '../api'
import { ToastContainer, toast } from 'react-toastify';
import {Link, useHistory} from "react-router-dom";
import {Button} from "react-bootstrap";
import "../../custom.css";
import StatLeave from "./StatLeave";

export default function AddLeave() {
    const notify = () => toast.success("Leave entry added successfully", {autoClose:2000});
    const history = useHistory();
    const [employeeId, setEmployeeId] = useState(0);
    const [requestType, setRequestType] = useState('');
    const [employee, setEmployee] = useState('nafis');
    const [description, setDescription] = useState('');
    const [startDate, setStartDate] = useState('');
    const [endDate, setEndDate] = useState('');
    const [leaveType, setLeaveType] = useState('');
    const [status, setStatus] = useState('');
    const [department,setDepartment]=useState('');
    const [comment,setComment]=useState('');


    async function tagSubmit(e){
            e.preventDefault();
            const data = {
                'employeeId': employeeId,
                'employee': employee,
                'requestType': requestType,
                'description': description,
                'startDate': startDate,
                'endDate': endDate,
                'leaveType': leaveType,
                'status': status,
                'department': department,
                'comment': comment
            };

            axios.post(`${base_url}/leave/lv`, data)
          .then(async (response) => {
              // setName("");
              // setWemail("");
              notify();
              await new Promise(resolve => setTimeout(resolve, 2000));
              history.push("/leave");
          }).catch((err) => {
            console.log( err.data );
          });
    }
    return (
        <div className='py-5'>
            <h2 className="text-center">Add Leave</h2>
            <ToastContainer />
            <form onSubmit={tagSubmit}>
            {/*<label for="fname" style={{ fontSize:"20px"}}>Id</label>*/}
            {/*<input type="text" id="fname" name="id" value={id} onChange={(e)=>setId(e.target.value)} />*/}
                <div className="d-flex align-items-center">
                    <label htmlFor="requestType" style={{fontSize: "15px", margin:"0 20px"}}>Request Type</label>
                    <input type="text" id="requestType" name="requestType" value={requestType} onChange={(e) => setRequestType(e.target.value)}/>
                    <label htmlFor="description" style={{fontSize: "15px", margin:"0 20px"}}>Description</label>
                    <input type="text" id="description" name="description" value={description}
                           onChange={(e) => setDescription(e.target.value)}/>
                </div>

                <div className="d-flex align-items-center">
                    <label htmlFor="startDate" style={{fontSize: "15px", margin:"0 20px"}}>StartDate</label>
                    <input type="date" id="startDate" name="startDate" value={startDate}
                           onChange={(e) => setStartDate(e.target.value)}/>
                    <label htmlFor="endDate" style={{fontSize: "15px", margin:"0 20px"}}>End Date</label>
                    <input type="date" id="endDate" name="endDate" value={endDate} onChange={(e) => setEndDate(e.target.value)}/>
                </div>

                <div className="d-flex align-items-center">
                    <label htmlFor="leaveType" style={{fontSize: "15px", margin:"0 20px"}}>Leave Type</label>
                    <input type="text" id="leaveType" name="leaveType" value={leaveType}
                           onChange={(e) => setLeaveType(e.target.value)}/>
                    <label htmlFor="status" style={{fontSize: "15px", margin:"0 20px"}}>Status</label>
                    <input type="text" id="status" name="status" value={status}
                           onChange={(e) => setStatus(e.target.value)}/>
                </div>

                <div className="d-flex align-items-center">
                    <label htmlFor="department" style={{fontSize: "15px", margin:"0 20px"}}>Department</label>
                    <input type="text" id="department" name="department" value={department}
                           onChange={(e) => setDepartment(e.target.value)}/>
                    <label htmlFor="comment" style={{fontSize: "15px", margin:"0 20px"}}>Comment</label>
                    <input type="text" id="comment" name="comment" value={comment}
                           onChange={(e) => setComment(e.target.value)}/>

                </div>

            <input type="submit" value="Submit" style={{ backgroundColor:"blue",fontSize:"22px", borderRadius:"10px", padding:"10px", color:"white"}}/>
                <Button type="button" className="m-3">
                    <Link to={"/leave"} style={{color: "white", textDecoration: "none"}}>Cancel</Link></Button>
            </form>


            <StatLeave/>
        </div>
    )
}
