import React,{useEffect,useState} from 'react'
import {useParams} from 'react-router-dom';
import base_url from '../api'
import { Link } from 'react-router-dom';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import { useHistory } from "react-router-dom";
import {Button, Modal} from "react-bootstrap";
import "../../custom.css";

export default function EmployeeInfo() {

    const notify = () => toast.success("Employee Updated Successfully", {autoClose:2000});
    const history = useHistory();
    const {id:tid}=useParams();
    const [name,setName]=useState('');
    const [waddress,setWaddress]=useState('');
    const [wmobile,setWmobile]=useState('');
    const [wemail,setWemail]=useState('');
    const [wlocation,setWlocation]=useState('');
    const [wphone,setWphone]=useState('');
    const [password,setPassword]=useState('');
    const [roles,setRoles]=useState('');
    const [department,setDepartment]=useState('');
    const [jobTitle,setJobTitle]=useState('');
    const [manager,setManager]=useState('');
    const [coach,setCoach]=useState('');
    const [nationality,setNationality]=useState('');
    const [identificationNo,setIdentificationNo]=useState('');
    const [passport,setPassport]=useState('');
    const [bankAccountNo,setBankAccountNo]=useState('');
    const [gender,setGender]=useState('');
    const [maritalStatus,setMaritalStatus]=useState('');
    const [homeAddress,setHomeAddress]=useState('');
    const [dateOfBirth,setDateOfBirth]=useState('');

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
        axios.get(`${base_url}/employees/emp/` + tid)
            .then(res => {
                    console.log(res.data);
                    setName(res.data.username);
                    setWemail(res.data.workEmail);
                    // setId(res.data.id);
                    setWphone(res.data.workPhone);
                    setWlocation(res.data.workLocation);
                    setWemail(res.data.workEmail);
                    setWaddress(res.data.workingAddress);
                    setWmobile(res.data.workMobile);
                    setPassword(res.data.password);
                    setRoles(res.data.roles);
                    setDateOfBirth(res.data.dateOfBirth);
                    setHomeAddress(res.data.homeAddress);
                    setBankAccountNo(res.data.bankAccountNo);
                    setDepartment(res.data.department);
                    setMaritalStatus(res.data.maritalStatus);
                    setNationality(res.data.nationality);
                    setPassport(res.data.passport);
                    setJobTitle(res.data.jobTitle);
                    setManager(res.data.manager);
                    setCoach(res.data.coach);
                    setIdentificationNo(res.data.identificationNo);
                    setGender(res.data.gender);
                },
                (error) => {
                    console.log(error);
                })
    }

    async function deleteCat(id) {
        await axios
            .delete(`${base_url}/employees/emp/`+id)
            .catch((error) => console.log(error.resp));
        notify();
        handleClose();
        history.push("/employee");
    }



    return (
        <div className='py-5'>
        <ToastContainer/>
            <h2 className="text-center">Employee Information</h2>
        <Button type="button" className="m-3">
            <Link to={"/editEmployee/" + tid} style={{color: "white", textDecoration: "none"}}>Edit</Link></Button>
            <Button type='button' onClick={() => handleShow(tid)} >
                 Delete
            </Button>
            <Button type="button" className="m-3">
                <Link to={"/employee/"} style={{color: "white", textDecoration: "none"}}>Go Back</Link></Button>
            <div className="d-flex  justify-content-between" style={{fontSize:"20px"}}>
                <p><b>ID: </b> {tid}</p>
                <p><b>Name: </b>{name}</p>
            </div>

            <h3>Contact Information</h3>
            <div className="d-flex  justify-content-between" style={{fontSize:"20px"}}>
                <p><b>Work Location: </b>{wlocation}</p>
                <p><b>Working Address: </b>{waddress}</p>
            </div>
            <div className="d-flex  justify-content-between" style={{fontSize:"20px"}}>
                <p><b>Work Phone: </b>{wphone}</p>
                <p><b>Work Mobile: </b>{wmobile}</p>
            </div>
            <div className="d-flex  justify-content-between" style={{fontSize:"20px"}}>
                <p><b>Work Email: </b>{wemail}</p>
                <p><b>Home Address: </b>{homeAddress}</p>
            </div>


            <h3>Position</h3>
            <div className="d-flex  justify-content-between" style={{fontSize:"20px"}}>
                <p><b>Department: </b>{department}</p>
                <p><b>Job Title: </b>{jobTitle}</p>
            </div>
            <div className="d-flex  justify-content-between" style={{fontSize:"20px"}}>
                <p><b>Manager: </b>{manager}</p>
                <p><b>Coach: </b>{coach}</p>
            </div>

            <h3>Citizenship & Other Information</h3>
            <div className="d-flex  justify-content-between" style={{fontSize:"20px"}}>
                <p><b>Nationality: </b>{nationality}</p>
                <p><b>Identification No: </b>{identificationNo}</p>
            </div>
            <div className="d-flex  justify-content-between" style={{fontSize:"20px"}}>
                <p><b>Passport No: </b>{passport}</p>
                <p><b>Bank Account Number: </b>{bankAccountNo}</p>
            </div>

            <h3>Status</h3>
            <div className="d-flex  justify-content-between" style={{fontSize:"20px"}}>
                <p><b>Gender: </b>{gender}</p>
                <p><b>Date of Birth: </b>{dateOfBirth}</p>
                <p><b>Marital Status: </b>{maritalStatus}</p>
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
