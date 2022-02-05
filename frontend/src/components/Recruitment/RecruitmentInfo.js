import React,{useEffect,useState} from 'react'
import {useParams} from 'react-router-dom';
import base_url from '../api'
import { Link } from 'react-router-dom';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import { useHistory } from "react-router-dom";
import {Button, Modal} from "react-bootstrap";
import "../../custom.css";

export default function RecruitmentInfo() {

    const notify = () => toast.success("Recruitment deleted Successfully", {autoClose:2000});
    const history = useHistory();
    const {id:tid}=useParams();
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
        axios.get(`${base_url}/recruitment/rec/` + tid)
            .then(res => {
                    console.log(res.data);
                    setName(res.data.name);
                    setCreationDate(res.data.creationDate);
                    setSubject(res.data.subject);
                    setEmail(res.data.email);
                    setPhone(res.data.phone);
                    setMobile(res.data.mobile);
                    setJobTitle(res.data.jobTitle);
                    setStage(res.data.stage);
                    setMedium(res.data.medium);
                    setSource(res.data.source);
                    setAppreciation(res.data.appreciation);
                    setResponsible(res.data.responsible);
                    setDepartment(res.data.department);
                    setDegree(res.data.degree);
                    setExpectedSalary(res.data.expectedSalary);
                    setProposedSalary(res.data.proposedSalary);
                    setAvailability(res.data.availability);
                },
                (error) => {
                    console.log(error);
                })
    }

    async function deleteCat(id) {
        await axios
            .delete(`${base_url}/recruitment/rec/`+id)
            .catch((error) => console.log(error.resp));
        notify();
        handleClose();
        history.push("/recruitment");
    }



    return (
        <div className='py-5'>
        <ToastContainer/>
            <h2 className="text-center">Recruitment Information</h2>
        <Button type="button" className="m-3">
            <Link to={"/editRecruitment/" + tid} style={{color: "white", textDecoration: "none"}}>Edit</Link></Button>
            <Button type='button' onClick={() => handleShow(tid)} >
                 Delete
            </Button>
            <Button type="button" className="m-3">
                <Link to={"/recruitment/"} style={{color: "white", textDecoration: "none"}}>Go Back</Link></Button>
            <div className="d-flex  justify-content-between" style={{fontSize:"20px"}}>
                <p><b>Subject/Application Name: </b> {subject}</p>
                <p><b>Name: </b>{name}</p>
            </div>

            <div className="d-flex  justify-content-between" style={{fontSize:"20px"}}>
                <p><b>Creation Date: </b>{creationDate}</p>
                <p><b>Email: </b>{email}</p>
            </div>
            <div className="d-flex  justify-content-between" style={{fontSize:"20px"}}>
                <p><b>Phone: </b>{phone}</p>
                <p><b>Mobile: </b>{mobile}</p>
            </div>
            <div className="d-flex  justify-content-between" style={{fontSize:"20px"}}>
                <p><b>Responsible: </b>{responsible}</p>
                <p><b>Degree: </b>{degree}</p>
            </div>


            <div className="d-flex  justify-content-between" style={{fontSize:"20px"}}>
                <p><b>Appreciation: </b>{appreciation}</p>
                <p><b>Source: </b>{source}</p>
            </div>

            <h3>Job Details</h3>
            <div className="d-flex  justify-content-between" style={{fontSize:"20px"}}>
                <p><b>Job Title: </b>{jobTitle}</p>
                <p><b>Department: </b>{department}</p>
            </div>

            <div className="d-flex  justify-content-between" style={{fontSize:"20px"}}>
                <p><b>Expected Salary: </b>{expectedSalary}</p>
                <p><b>Proposed Salary: </b>{proposedSalary}</p>
            </div>
            <div className="d-flex  justify-content-between" style={{fontSize:"20px"}}>
                <p><b>Medium: </b>{medium}</p>
                <p><b>Stage: </b>{stage}</p>
                <p><b>Availability: </b>{availability}</p>
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

        </div>
    )
}
