import React, {useEffect, useState} from "react";
import axios from "axios";
import base_url from "../api";
import {ToastContainer} from "react-toastify";
import {Button} from "react-bootstrap";
import {Link} from "react-router-dom";
import dateFormat from "dateformat";

export default function StatLeave(){

    const [data, setData] = useState([]);

    useEffect(() => {
        getData();
    }, []);
    async function getData() {
        axios.get(`${base_url}/leave/stat`)
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
            <h2 className="text-center">Available Leaves</h2>
            <div className="d-flex  justify-content-between" style={{fontSize:"20px"}}>
                <p><b>Legal Leaves Remaining: </b>{data["legalLeaves"]}</p>
                <p><b>Compansatory Leaves Remaining: </b>{data["compensatoryLeaves"]}</p>
                <p><b>Unpaid Leaves Remaining: </b>{data["unpaidLeaves"]}</p>
            </div>
        </div>
    )
}