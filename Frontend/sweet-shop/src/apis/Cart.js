import axios from "axios";
axios.defaults.withCredentials = true

export async function addProdToCart(addProdToCart) {
  return await axios.post('http://localhost:8085/SweetShop/addProdToCart', addProdToCart)
}

export async function getCart(userId) {
  return await axios.get(`http://localhost:8085/SweetShop/cart/${userId}`)
}

export async function deleteProdFromCart(deleteProdFromCart) {
  return await axios.delete('http://localhost:8085/SweetShop/deleteProdFromCart', deleteProdFromCart)
}