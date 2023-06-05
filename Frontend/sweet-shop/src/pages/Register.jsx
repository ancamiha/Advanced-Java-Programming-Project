import React, { useState } from 'react';
import { onRegistration } from "./../apis/Auth";
import { BsPersonFill } from 'react-icons/bs';
import { MdEmail } from 'react-icons/md';
import { RiShieldKeyholeFill } from 'react-icons/ri';
import { BsTelephoneFill } from 'react-icons/bs';
import '../styles/auth.css';
import { useNavigate } from "react-router-dom";

const Register = () => {
  const [values, setValues] = useState({
    name: '',
    email: '',
    phoneNumber: '',
    password: '',
  });

  const [error, setError] = useState(false);
  const [success, setSuccess] = useState(false);

  const onChange = (e) => {
    setValues({ ...values, [e.target.name]: e.target.value })
  }

  const navigate = useNavigate();

  const onSubmit = async (e) => {
    e.preventDefault()

    try {
      const { data } = await onRegistration(values)
      setError('')
      setSuccess(data.msg)
      console.log(data.msg)
      setValues({ username: '', phoneNumber: '', email: '', password: '' })
      navigate('/');
    } catch (error) {
      console.log(error.response.data.msg)
      setError(error.response.data.msg)
      setSuccess('')
    }
  }

  const handleBackClick = () => {
    navigate('/');
  };

  return (
    <div className='auth_container'>
      <form onSubmit={(e) => onSubmit(e)} className='form__container'>
        <h1>Register</h1>

        <div className='mini__container'>
          <label className='form-label'>
            Name
          </label>
          <div className='input-wrapper'>
            <BsPersonFill size='1.5em' className='icon' />
            <input
              onChange={(e) => onChange(e)}
              type='text'
              className='form-control'
              id='name'
              name='name'
              value={values.name}
              placeholder='Jane Doe'
              required
            />
          </div>
        </div>

        <div className='mini__container'>
          <label htmlFor='email' className='form-label'>
            Phone number
          </label>
          <div className='input-wrapper'>
            <BsTelephoneFill size='1.3em' className='icon' />
            <input
              onChange={(e) => onChange(e)}
              type='phoneNumber'
              className='form-control'
              id='phoneNumber'
              name='phoneNumber'
              value={values.phoneNumber}
              placeholder='07xxxxxxxx'
              required
            />
          </div>
        </div>

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
        <div className='auth__message' style={{ color: 'green', margin: '10px 0' }}>{success}</div>
        <div>
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

export default Register