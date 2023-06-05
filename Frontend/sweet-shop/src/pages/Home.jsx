import React from 'react';
import { useNavigate } from "react-router-dom";
import logo from "../assets/logo.png"
import '../styles/home.css';

function Home() {
  const navigate = useNavigate();

  const handleLoginClick = () => {
      navigate('/login');
  };
    
  const handleRegisterClick = () => {
    navigate('/register');
  };

  return (
    <div className='container'>
      <h1>Welcome to my Candy Shop!</h1>
      <img src={logo} alt="mylogo"/>
      <div className='buttons'> 
        <button className="buttonStyle" onClick={handleLoginClick}>Login</button>
        <button className="buttonStyle" onClick={handleRegisterClick}>Register</button>
      </div>
    </div>
  );
}

export default Home;
