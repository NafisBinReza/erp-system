import React,{useEffect,useState} from 'react'
import axios from 'axios';
import base_url from '../api'
import { Link } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import {Button, Modal} from "react-bootstrap";
import "../../custom.css";
import {format} from "date-fns";
import dateFormat from "dateformat";
import DatePicker from 'react-date-picker';
import StatLeave from "./StatLeave";

export default function Employee() {
    const [data, setData] = useState([]);
    const [value, onChange] = useState(new Date());
    let dateCounter = 0;

    useEffect(() => {
        getData();
    }, []);
    async function getData() {
        axios.get(`${base_url}/leave/all`)
            .then(res => {
                    console.log(res.data);
                    setData(res.data);
                },
                (error)=>{
                    console.log(error);
                })
    }

    return (
        <div className='py-5'>
            <ToastContainer />
            <h2 className="text-center">Leave List</h2>
            <Button type="button" className="mb-3">
                <Link to={"/addLeave"} style={{color:"white", textDecoration:"none"}}>Create New Leave</Link></Button>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Employee</th>
                    <th scope="col">Request Type</th>
                    <th scope="col">Description</th>
                    <th scope="col">Number of Days</th>
                    <th scope="col">Start Date</th>
                    <th scope="col">End Date</th>
                    <th scope="col">Leave Type</th>
                    <th scope="col">Status</th>
                    <th scope="col">Action</th>
                </tr>
                </thead>
                <tbody>
                {data.map((item) =>(
                    <tr>
                        <td>{item.employee}</td>
                        <td>{item.requestType}</td>
                        <td>{item.description}</td>
                        <td>{item.numberOfDays}</td>
                        <td>{dateFormat(item.startDate)}</td>
                        <td>{dateFormat(item.endDate)}</td>
                        <td>{item.leaveType}</td>
                        <td>{item.status}</td>
                        <td class="d-flex">
                            <Button type="button" style={{marginRight:'3%'}}>
                                <Link to={"/leaveInfo/"+item.id} style={{color:"white", textDecoration:"none"}}>Info</Link></Button>

                        </td>
                    </tr>

                ))}
                </tbody>
            </table>

            {data.forEach(e => (
                dateCounter = dateCounter + e.numberOfDays
            ))}

            <h5 style={{marginLeft:"10%"}}>Total number of Leave Days Remaining: {dateCounter}</h5>

            <StatLeave/>
        </div>


    )
}
