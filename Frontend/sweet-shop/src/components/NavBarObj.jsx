import React from 'react';
import { AiOutlineShoppingCart } from 'react-icons/ai';
import '../styles/navbarobj.css';
import { useNavigate } from "react-router-dom";

function NavBarObj () {
    const navigate = useNavigate();
    const handleCartClick = () => {
        navigate('/cart');
      };

    return (  
        <div className='container_hdr'>
            <div className='container_cart'>
                <button className='buttonStyle cart' onClick={ handleCartClick }>
                    <AiOutlineShoppingCart size='1.5em'/>
                </button>
            </div>
        </div>
    );
}

export default NavBarObj