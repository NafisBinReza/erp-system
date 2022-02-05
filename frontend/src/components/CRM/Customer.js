import React,{useEffect,useState} from 'react'
import axios from 'axios';
import base_url from '../api'
import { Link } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import {Button, Modal} from "react-bootstrap";
import "../../custom.css";

export default function Customer() {
    const [data, setData] = useState([]);

    useEffect(() => {
        getData();
        }, []);
        async function getData() {
            axios.get(`${base_url}/crm/all`)
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
            <h2 className="text-center">Customer List</h2>
            <Button type="button" className="mb-3">
                <Link to={"/addCustomer"} style={{color:"white", textDecoration:"none"}}>Create New Customer</Link></Button>
            <table class="table">
                <thead>
                    <tr>
                    <th scope="col">Name</th>
                        <th scope="col">Phone</th>
                    <th scope="col">Email</th>
                    <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>
                {data.map((item) =>(
                                      <tr>
                                        <td>{item.name}</td>
                                          <td>{item.phone}</td>
                                          <td>{item.email}</td>

                                        <td class="d-flex">
                                            <Button type="button" style={{marginRight:'3%'}}>
                                        <Link to={"/customerInfo/"+item.id} style={{color:"white", textDecoration:"none"}}>Info</Link></Button>

                                        </td>
                                    </tr>
                                      ))}
                </tbody>
            </table>



        </div>


    )
}
