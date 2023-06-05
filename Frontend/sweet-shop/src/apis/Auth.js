import axios from 'axios'
axios.defaults.withCredentials = true


export async function onRegistration(registrationData) {
  return await axios.post('http://localhost:8085/SweetShop/register', registrationData)
}

export async function onLogin(loginData) {
  return await axios.post('http://localhost:8085/SweetShop/login', loginData)
}

export async function getCookie() {
  return await axios.get('http://localhost:8085/SweetShop/get-user-id-cookie')
}

export async function checkLogin() {
  return await axios.get('http://localhost:8085/SweetShop/check-login')
}

// export async function onLogout() {
//   return await axios.delete('http://localhost:8000/logout')
// }