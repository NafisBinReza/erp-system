import React,{useEffect,useState} from 'react'
import {Link, useParams} from 'react-router-dom';
import base_url from '../api'
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import { useHistory } from "react-router-dom";
import {Button} from "react-bootstrap";
import "../../custom.css";

export default function EditCustomer() {
    const notify = () => toast.success("Customer Updated Successfully", {autoClose:2000});
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

        async function categoryEdit(e) {
            e.preventDefault();

            const data = {
                'id': tid,
                'name':name,
                'address':address,
                'city':city,
                'state':state,
                'zipCode':zipCode,
                'website':website,
                'phone':phone,
                'mobile':mobile,
                'fax':fax,
                'email':email,
                'language':language
            };
            axios
                .put(`${base_url}/crm/customer/`, data)
                .then(async (response) => {
                    notify();

                    await new Promise(resolve => setTimeout(resolve, 2000));
                    history.push("/customerInfo/" + tid)
                }).catch((err) => {
                console.log(err.data);
            });

        }

        return (
            <div className='py-5'>
                <ToastContainer/>
                <h2 className="text-center">Edit Customer</h2>
                <form onSubmit={categoryEdit}>
                    <div className="d-flex align-items-center">
                        <label htmlFor="name" style={{fontSize: "15px", margin:"0 20px"}}>Name</label>
                        <input type="text" id="name" name="name" value={name} onChange={(e) => setName(e.target.value)}/>
                        <label htmlFor="address" style={{fontSize: "15px", margin:"0 20px"}}>Address</label>
                        <input type="text" id="address" name="address" value={address} onChange={(e) => setAddress(e.target.value)}/>
                    </div>
                    <div className="d-flex align-items-center">
                        <label htmlFor="city" style={{fontSize: "15px", margin:"0 20px"}}>City</label>
                        <input type="text" id="city" name="city" value={city} onChange={(e) => setCity(e.target.value)}/>
                        <label htmlFor="state" style={{fontSize: "15px", margin:"0 20px"}}>State</label>
                        <input type="text" id="state" name="state" value={state} onChange={(e) => setState(e.target.value)}/>
                    </div>
                    <div className="d-flex align-items-center">
                        <label htmlFor="zipCode" style={{fontSize: "15px", margin:"0 20px"}}>ZIP</label>
                        <input type="text" id="zipCode" name="zipCode" value={zipCode} onChange={(e) => setZipCode(e.target.value)}/>
                        <label htmlFor="website" style={{fontSize: "15px", margin:"0 20px"}}>Website</label>
                        <input type="text" id="website" name="website" value={website} onChange={(e) => setWebsite(e.target.value)}/>
                    </div>
                    <div className="d-flex align-items-center">
                        <label htmlFor="phone" style={{fontSize: "15px", margin:"0 20px"}}>Phone</label>
                        <input type="text" id="phone" name="phone" value={phone} onChange={(e) => setPhone(e.target.value)}/>
                        <label htmlFor="mobile" style={{fontSize: "15px", margin:"0 20px"}}>Mobile</label>
                        <input type="text" id="mobile" name="mobile" value={mobile} onChange={(e) => setMobile(e.target.value)}/>
                    </div>
                    <div className="d-flex align-items-center">
                        <label htmlFor="fax" style={{fontSize: "15px", margin:"0 20px"}}>Fax</label>
                        <input type="text" id="fax" name="fax" value={fax} onChange={(e) => setFax(e.target.value)}/>
                        <label htmlFor="email" style={{fontSize: "15px", margin:"0 20px"}}>Email</label>
                        <input type="text" id="email" name="email" value={email} onChange={(e) => setEmail(e.target.value)}/>
                        <label htmlFor="language" style={{fontSize: "15px", margin:"0 20px"}}>Language</label>
                        <input type="text" id="language" name="language" value={language} onChange={(e) => setLanguage(e.target.value)}/>
                    </div>

                    <input type="submit" value="Submit" style={{ backgroundColor:"blue",fontSize:"22px", borderRadius:"10px", padding:"10px", color:"white"}}/>
                    <Button type="button" className="m-3">
                        <Link to={"/customerInfo/" + tid} style={{color: "white", textDecoration: "none"}}>Cancel</Link></Button>
                </form>
            </div>
        )
    }
