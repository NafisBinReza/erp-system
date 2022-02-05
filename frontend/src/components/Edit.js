import React,{useEffect,useState} from 'react'
import {useParams} from 'react-router-dom';
import base_url from '../api'
import { Link } from 'react-router-dom';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import { useHistory } from "react-router-dom";
import { yupResolver } from "@hookform/resolvers/yup";
import * as yup from "yup";
import {useForm} from "react-hook-form";
const schema = yup.object().shape({
    email: yup.string().email().required(),
    name: yup.string().min(3).max(32).required(),
});
export default function Edit() {
    const notify = () => toast.success("Employee Updated Successfully", {autoClose:2000});
    const history = useHistory();
    const {id:tid}=useParams();
    const [id,setId]=useState('');
    const [name,setName]=useState('');
    const [email,setEmail]=useState('');

        const {
            register,
            handleSubmit,
            formState: {errors},
            reset,
        } = useForm({
            resolver: yupResolver(schema),
        });

        const login = (data) => {
            categoryEdit(data).then();
            reset();
        };

        useEffect(() => {
            getData();
        }, []);

        async function getData() {
            axios.get(`${base_url}/employees/` + tid)
                .then(res => {
                        console.log(res.data);
                        setName(res.data.name);
                        setEmail(res.data.email);
                        setId(res.data.id);
                    },
                    (error) => {
                        console.log(error);
                    })
        }

        async function categoryEdit(e) {
            e.preventDefault();

            const data = {
                'id': id,
                'name': name,
                'email': email
            };
            axios
                .put(`${base_url}/employees`, data)
                .then(async (response) => {
                    setName("");
                    setEmail("");
                    setId("");
                    notify();

                    await new Promise(resolve => setTimeout(resolve, 2000));
                    history.push("/student")
                }).catch((err) => {
                console.log(err.data);
            });

        }

        return (
            <div className='py-5'>
                <ToastContainer/>
                <form onSubmit={categoryEdit}>
                    <label for="fname" style={{fontSize: "20px"}}>Id</label>
                    <input type="text" id="fname" name="id" value={id} onChange={(e) => setId(e.target.value)}
                           readOnly/>
                    <label for="fname" style={{fontSize: "20px"}}>Name</label>
                    <input type="text" id="fname" name="name" value={name} onChange={(e) => setName(e.target.value)}/>
                    <label for="lname" style={{fontSize: "20px"}}>Email</label>
                    <input type="text" id="lname" name="email"  {...register("email")} value={email}
                           onChange={(e) => setEmail(e.target.value)}/>
                    <p>{errors.email?.message}</p>
                    <input type="submit" value="Submit" style={{
                        backgroundColor: "blue",
                        fontSize: "22px",
                        color: "white",
                        borderRadius: "10px",
                        padding: "10px"
                    }}/>
                </form>
            </div>
        )
    }
