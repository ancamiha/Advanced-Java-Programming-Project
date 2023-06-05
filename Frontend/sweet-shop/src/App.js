import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Register from './pages/Register';
import Login from './pages/Login';
import Home from './pages/Home';
import Products from './pages/Products';
import CartContent from './pages/CartContent';
import './App.css';
import Navbar from './components/Navbar';

export default function App() {
  return (
    <>
      <Router>
        <Navbar />
        <Routes>
          <Route exact path='/' element={<Home />} />
          <Route path='/register' element={<Register />} />
          <Route path='/login' element={<Login />} />
          <Route path='/products' element={<Products />}/>
          <Route path='/cart' element={<CartContent />} />
        </Routes>
      </Router>
    </>
  );
}
