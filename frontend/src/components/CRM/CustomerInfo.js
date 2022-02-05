import React,{useEffect,useState} from 'react'
import {useParams} from 'react-router-dom';
import base_url from '../api'
import { Link } from 'react-router-dom';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import { useHistory } from "react-router-dom";
import {Button, Modal} from "react-bootstrap";
import "../../custom.css";

export default function CustomerInfo() {

    const notify = () => toast.success("Customer Deleted Successfully", {autoClose:2000});
    const history = useHistory();
    const {id:tid}=useParams();
    const [name,setName]=useState('');
    const [address,setAddress]=useState('');
    const [city,setCity]=useState('');
    const [state,setState]=useState('');
    const [zipCode,setZipCode]=useState('');
    const [website,setWebsite]=useState('');
    const [phone,setPhone]=useState('');
    const [mobile,setMobile]=useState('');
    const [fax,setFax]=useState('');
    const [email,setEmail]=useState('');
    const [language,setLanguage]=useState('');

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
        axios.get(`${base_url}/crm/customer/` + tid)
            .then(res => {
                    console.log(res.data);
                    setName(res.data.name);
                    setAddress(res.data.address);
                    setCity(res.data.city);
                    setState(res.data.state);
                    setZipCode(res.data.zipCode);
                    setWebsite(res.data.website);
                    setPhone(res.data.phone);
                    setMobile(res.data.mobile);
                    setFax(res.data.fax);
                    setEmail(res.data.email);
                    setLanguage(res.data.language);
                },
                (error) => {
                    console.log(error);
                })
    }

    async function deleteCat(id) {
        await axios
            .delete(`${base_url}/crm/customer/`+id)
            .catch((error) => console.log(error.resp));
        notify();
        handleClose();
        history.push("/customer");
    }



    return (
        <div className='py-5'>
        <ToastContainer/>
            <h2 className="text-center">Customer Information</h2>
        <Button type="button" className="m-3">
            <Link to={"/editCustomer/" + tid} style={{color: "white", textDecoration: "none"}}>Edit</Link></Button>
            <Button type='button' onClick={() => handleShow(tid)} >
                 Delete
            </Button>
            <Button type="button" className="m-3">
                <Link to={"/customer/"} style={{color: "white", textDecoration: "none"}}>Go Back</Link></Button>
            <div className="d-flex  justify-content-between" style={{fontSize:"20px"}}>
                <p><b>ID: </b> {tid}</p>
                <p><b>Name: </b>{name}</p>
            </div>

            <div className="d-flex  justify-content-between" style={{fontSize:"20px"}}>
                <p><b>Address: </b> {address}</p>
                <p><b>City: </b>{city}</p>
            </div>
            <div className="d-flex  justify-content-between" style={{fontSize:"20px"}}>
                <p><b>State: </b> {state}</p>
                <p><b>ZIP: </b>{zipCode}</p>
            </div>
            <div className="d-flex  justify-content-between" style={{fontSize:"20px"}}>
                <p><b>Website: </b> {website}</p>
                <p><b>Fax: </b>{fax}</p>
            </div>
            <div className="d-flex  justify-content-between" style={{fontSize:"20px"}}>
                <p><b>Phone: </b> {phone}</p>
                <p><b>Mobile: </b>{mobile}</p>
            </div>
            <div className="d-flex  justify-content-between" style={{fontSize:"20px"}}>
                <p><b>Email: </b> {email}</p>
                <p><b>Language: </b>{language}</p>
            </div>



            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title> Confirm Delete? </Modal.Title>
                </Modal.Header>
                <Modal.Body>Are you sure you want to delete this Customer?</Modal.Body>
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
