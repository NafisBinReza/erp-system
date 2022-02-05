import React,{useEffect,useState} from 'react'
import axios from 'axios';
import base_url from '../api'
import { Link } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import {Button, Modal} from "react-bootstrap";
import "../../custom.css";

export default function Jobs() {
    const [data, setData] = useState([]);

    useEffect(() => {
        getData();
        }, []);
        async function getData() {
            axios.get(`${base_url}/recruitment/jobs`)
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
            <h2 className="text-center">Job Status</h2>
            <Button type="button" className="m-3">
                <Link to={"/recruitment/"} style={{color: "white", textDecoration: "none"}}>Go Back</Link></Button>
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Job Title</th>
                        <th scope="col">Department</th>
                        <th scope="col">Current Number of Employee</th>
                        <th scope="col">Expected New Employees</th>
                        <th scope="col">Total Forecasted Employees</th>
                        <th scope="col">Hired Employees</th>
                        <th scope="col">Status</th>
                    </tr>
                </thead>
                <tbody>
                {data.map((item) =>(
                                      <tr>
                                          <td>{item.jobTitle}</td>
                                          <td>{item.department}</td>
                                          <td>{item.currentEmployees}</td>
                                          <td>{item.expectedEmployees}</td>
                                          <td>{item.forecastedEmployees}</td>
                                          <td>{item.hiredEmployees}</td>
                                          <td>{item.status}</td>
                                      </tr>
                                      ))}
                </tbody>
            </table>



        </div>


    )
}
