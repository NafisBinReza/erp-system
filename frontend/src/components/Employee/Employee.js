import React,{useEffect,useState} from 'react'
import axios from 'axios';
import base_url from '../api'
import { Link } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import {Button, Modal} from "react-bootstrap";
import "../../custom.css";

export default function Employee() {
    const [data, setData] = useState([]);
    const notify = () => toast.info("Employee Deleted", {autoClose: 2000});




    useEffect(() => {
        getData();
        }, []);
        async function getData() {
            axios.get(`${base_url}/employees/all`)
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
            <h2 className="text-center">Employee List</h2>
            <Button type="button" className="mb-3">
                <Link to={"/addEmployee"} style={{color:"white", textDecoration:"none"}}>Create New Employee</Link></Button>
            <table class="table">
                <thead>
                    <tr>
                    <th scope="col">Name</th>
                        <th scope="col">Work Phone</th>
                    <th scope="col">Email</th>
                        <th scope="col">Department</th>
                        <th scope="col">Job Title</th>
                        <th scope="col">Manager</th>
                    <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>
                {data.map((item) =>(
                                      <tr>
                                        <td>{item.username}</td>
                                          <td>{item.workPhone}</td>
                                        <td>{item.workEmail}</td>
                                          <td>{item.department}</td>
                                          <td>{item.jobTitle}</td>
                                          <td>{item.manager}</td>
                                        <td class="d-flex">
                                            <Button type="button" style={{marginRight:'3%'}}>
                                        <Link to={"/employeeInfo/"+item.id} style={{color:"white", textDecoration:"none"}}>Info</Link></Button>

                                        </td>
                                    </tr>
                                      ))}
                </tbody>
            </table>



        </div>


    )
}
