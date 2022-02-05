import React,{useEffect,useState} from 'react'
import axios from 'axios';
import base_url from '../api'
import { Link } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import {Button, Modal} from "react-bootstrap";

export default function Employee() {
    const [data, setData] = useState([]);
    const notify = () => toast.info("Employee Deleted", {autoClose: 2000});

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
            axios.get(`${base_url}/employees/all`)
                .then(res => {
                    console.log(res.data);
                    setData(res.data);
                },
                (error)=>{
                    console.log(error);
                })
          }

          async function deleteCat(id) {
            await axios
                .delete(`${base_url}/employees/`+id)
                .catch((error) => console.log(error.resp));
            notify();
            handleClose();
            getData();
          }
    return (
        <div className='py-5'>
            <ToastContainer />
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
                                        <Link to={"/edit/"+item.id} style={{color:"white", textDecoration:"none"}}>Info</Link></Button>
                                           {/*<Button type='button' onClick={() => handleShow(item)} >*/}
                                           {/*     Delete*/}
                                           {/*</Button>*/}
                                        </td>
                                    </tr>
                                      ))}
                </tbody>
            </table>

            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title> Confirm Delete? </Modal.Title>
                </Modal.Header>
                <Modal.Body>Are you sure you want to delete this employee?</Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Close
                    </Button>
                    <Button variant="primary" onClick={() => deleteCat(target.id)}>
                        Confirm
                    </Button>
                </Modal.Footer>
            </Modal>

        </div>


    )
}
