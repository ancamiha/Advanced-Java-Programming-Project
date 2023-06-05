import React, { useEffect, useState } from 'react';
import '../styles/navbar.css'
import { NavLink } from 'react-router-dom'
import NavBarObj from './NavBarObj'
import { checkLogin } from '../apis/Auth';

function Navbar() {
  const [ loggedIn, setLoggedIn ] = useState("");
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await checkLogin();
        // console.log(response.data);
        setLoggedIn(response.data);
      } catch (error) { }
    };

    fetchData();
  }, []);

  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light">
        <div className="navbar-nav">
          <NavLink to='/' className="navbar-brand">SweetShop</NavLink>
          <NavLink to='/products' className="nav-item nav-link active">Products</NavLink>
        </div>
        {!loggedIn ? (<div className="auth_cart navbar-nav">
            <NavLink to='/login' className="nav-item nav-link active">Login</NavLink>
            <NavLink to='/register' className="nav-item nav-link">Register</NavLink>
            <NavBarObj />
          </div>
        ) : (
          <div className="auth_cart navbar-nav">
            <NavBarObj />
          </div>
        )}
    </nav>
  )
}

export default Navbar