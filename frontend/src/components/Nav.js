import React from 'react'
import "../nav.css"
import {
    Link
  } from "react-router-dom";

export default function Nav() {
    return (
        <div>
            <ul>
                <li><Link to="/">Home</Link></li>
                <li><Link to="/employee">Employees</Link></li>
                <li><Link to="/leave">Leave</Link></li>
                <li><Link to="/recruitment">Recruitment</Link></li>
                <li><Link to="/customer">CRM</Link></li>
                <li><Link to="/about">About</Link></li>
            </ul>
        </div>
    )
}
