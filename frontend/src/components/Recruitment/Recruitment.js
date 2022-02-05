import React,{useEffect,useState} from 'react'
import axios from 'axios';
import base_url from '../api'
import { Link } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import {Button, Modal} from "react-bootstrap";
import "../../custom.css";

export default function Recruitment() {
    const [data, setData] = useState([]);

    useEffect(() => {
        getData();
        }, []);
        async function getData() {
            axios.get(`${base_url}/recruitment/all`)
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
            <h2 className="text-center">Recruitment List</h2>
            <Button type="button" className="m-3">
                <Link to={"/addRecruitment"} style={{color:"white", textDecoration:"none"}}>Create New Recruitment</Link></Button>
            <Button type="button" className="m-3">
                <Link to={"/jobs"} style={{color: "white", textDecoration: "none"}}>Job Status</Link></Button>
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Creation Date</th>
                        <th scope="col">Subject/Application Name</th>
                        <th scope="col">Applicant's Name</th>
                        <th scope="col">Email</th>
                        <th scope="col">Phone</th>
                        <th scope="col">Applied Job</th>
                        <th scope="col">Stage</th>
                        <th scope="col">Medium</th>
                        <th scope="col">Source</th>
                        <th scope="col">Appreciation</th>
                        <th scope="col">Responsibility</th>
                    <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>
                {data.map((item) =>(
                                      <tr>
                                        <td>{item.creationDate}</td>
                                          <td>{item.subject}</td>
                                        <td>{item.name}</td>
                                          <td>{item.email}</td>
                                          <td>{item.phone}</td>
                                          <td>{item.jobTitle}</td>
                                          <td>{item.stage}</td>
                                          <td>{item.medium}</td>
                                          <td>{item.source}</td>
                                          <td>{item.appreciation}</td>
                                          <td>{item.responsible}</td>
                                        <td class="d-flex">
                                            <Button type="button" style={{marginRight:'3%'}}>
                                        <Link to={"/recruitmentInfo/"+item.id} style={{color:"white", textDecoration:"none"}}>Info</Link></Button>

                                        </td>
                                    </tr>
                                      ))}
                </tbody>
            </table>



        </div>


    )
}
