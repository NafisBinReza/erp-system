import React,{useEffect,useState} from 'react'
import {useParams} from 'react-router-dom';
import base_url from '../api'
import { Link } from 'react-router-dom';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import { useHistory } from "react-router-dom";
import {Button, Modal} from "react-bootstrap";
import "../../custom.css";
import StatLeave from "./StatLeave";

export default function LeaveInfo() {

    const notify = () => toast.success("Employee Updated Successfully", {autoClose:2000});
    const history = useHistory();
    const {id:tid}=useParams();
    const [employeeId, setEmployeeId] = useState(0);
    const [requestType, setRequestType] = useState('');
    const [employee, setEmployee] = useState('nafis');
    const [description, setDescription] = useState('');
    const [startDate, setStartDate] = useState('');
    const [endDate, setEndDate] = useState('');
    const [numberOfDays, setNumberOfDays] = useState("");
    const [leaveType, setLeaveType] = useState('');
    const [status, setStatus] = useState('');
    const [department,setDepartment]=useState('');
    const [comment,setComment]=useState('');

    const [show, setShow] = useState(false);
    const [target, setTarget] = useState(null);

    const handleClose = () => setShow(false);
    const handleShow = item => {
        setTarget(item)
        return setShow(true);
    }


    useEffect(() => {
        getData();
    }, []);

    async function getData() {
        axios.get(`${base_url}/leave/lv/` + tid)
            .then(res => {
                    console.log(res.data);
                    setEmployeeId(res.data.employeeId);
                    setRequestType(res.data.requestType);
                    setEmployee(res.data.employee);
                    setDescription(res.data.description);
                    setStartDate(res.data.startDate);
                    setEndDate(res.data.endDate);
                    setNumberOfDays(res.data.numberOfDays);
                    setLeaveType(res.data.leaveType);
                    setStatus(res.data.status);
                    setDepartment(res.data.department);
                    setComment(res.data.comment);

                },
                (error) => {
                    console.log(error);
                })
    }

    async function deleteCat(id) {
        await axios
            .delete(`${base_url}/leave/lv/`+id)
            .catch((error) => console.log(error.resp));
        notify();
        handleClose();
        history.push("/leave");
    }



    return (
        <div className='py-5'>
        <ToastContainer/>
        <Button type="button" className="m-3">
            <Link to={"/editLeave/" + tid} style={{color: "white", textDecoration: "none"}}>Edit</Link></Button>
            <Button type='button' onClick={() => handleShow(tid)} >
                 Delete
            </Button>
            <Button type="button" className="m-3">
                <Link to={"/leave"} style={{color: "white", textDecoration: "none"}}>Go Back</Link></Button>
            <div className="d-flex  justify-content-between" style={{fontSize:"20px"}}>
                <p><b>ID: </b> {employeeId}</p>
                <p><b>Employee Name: </b>{employee}</p>
            </div>

            <div className="d-flex  justify-content-between" style={{fontSize:"20px"}}>
                <p><b>Request Type: </b>{requestType}</p>
                <p><b>Description: </b>{description}</p>
            </div>
            <div className="d-flex  justify-content-between" style={{fontSize:"20px"}}>
                <p><b>Start Date: </b>{startDate}</p>
                <p><b>End Date: </b>{endDate}</p>
            </div>
            <div className="d-flex  justify-content-between" style={{fontSize:"20px"}}>
                <p><b>Number of Days: </b>{numberOfDays * -1}</p>
                <p><b>Leave Type: </b>{leaveType}</p>
            </div>

            <div className="d-flex  justify-content-between" style={{fontSize:"20px"}}>
                <p><b>Status: </b>{status}</p>
                <p><b>Department: </b>{department}</p>
            </div>
            <div className="d-flex  justify-content-center" style={{fontSize:"20px"}}>
                <p><b>Comment By Manager: </b>{comment}</p>
            </div>

            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title> Confirm Delete? </Modal.Title>
                </Modal.Header>
                <Modal.Body>Are you sure you want to delete this employee?</Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Close
                    </Button>
                    <Button variant="primary" onClick={() => deleteCat(target)}>
                        Confirm
                    </Button>
                </Modal.Footer>
            </Modal>

            <StatLeave/>
        </div>
    )
}
