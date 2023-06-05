import { useState } from 'react';
import { onLogin } from "./../apis/Auth";
import '../styles/auth.css';
import { RiShieldKeyholeFill } from 'react-icons/ri';
import { MdEmail } from 'react-icons/md';
import { useNavigate } from "react-router-dom";

const Login = () => {
  const [values, setValues] = useState({
    email: '',
    password: '',
  })
  const [error, setError] = useState(false)

  const onChange = (e) => {
    setValues({ ...values, [e.target.name]: e.target.value })
  }

  const navigate = useNavigate();

  const onSubmit = async (e) => {
    e.preventDefault()
    try {
      await  onLogin(values)
      navigate('/products');
    } catch (error) {
      console.log(error.response)
      setError(error.response)
    }
  }

  const handleBackClick = () => {
    navigate('/');
  };

  return (
    <div className='auth_container'>
        <form onSubmit={(e) => onSubmit(e)} className='form__container'>
        <h1>Login</h1>
        <div className='mini__container'>
            <label htmlFor='email' className='form-label'>
                Email address
            </label>
            <div className='input-wrapper'>
                <MdEmail size='1.5em' className='icon' />
                    <input
                    onChange={(e) => onChange(e)}
                    type='email'
                    className='form-control'
                    id='email'
                    name='email'
                    value={values.email}
                    placeholder='example@email.com'
                    required
                    />
            </div>
        </div>
        <div className='mini__container'>
            <label htmlFor='password' className='form-label'>
                Password
            </label>
            <div className='input-wrapper'>
                <RiShieldKeyholeFill size='1.5em' className='icon' />
                <input
                onChange={(e) => onChange(e)}
                type='password'
                value={values.password}
                className='form-control'
                id='password'
                name='password'
                placeholder='password'
                required
                />
            </div>
        </div>
        <div className='auth__message' style={{ color: 'red', margin: '10px 0' }}>{error}</div>
            <div className='centerize'>
                <button type='submit' className='buttonStyle'>
                    Submit
                </button>
            </div>
        </form>

        <div className='back_btn'>
            <button type='submit' className='buttonStyle' onClick={ handleBackClick }>
                Back
            </button>
        </div>
    </div>
  )
}

export default Login