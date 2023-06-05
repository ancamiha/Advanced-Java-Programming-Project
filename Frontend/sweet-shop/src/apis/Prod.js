import axios from "axios";
axios.defaults.withCredentials = true

export async function getProducts() {
  return await axios.get('http://localhost:8085/SweetShop/sweets')
}