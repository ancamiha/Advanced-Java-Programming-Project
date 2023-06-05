import React, { useEffect, useState } from 'react';
import { getProducts } from '../apis/Prod';
import "../styles/table.css"
import { MdAdd } from 'react-icons/md';
import { getCookie } from '../apis/Auth';
import { addProdToCart } from '../apis/Cart'

const ProductList = () => {

  const [ userId, setUserId ] = useState("");
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await getCookie();
        // console.log(response.data);
        setUserId(response.data);
      } catch (error) { }
    };

    fetchData();
  }, []);

  const [ products, setProducts ] = useState("");
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await getProducts();
        // console.log(response.data);
        setProducts(response.data);
      } catch (error) { }
    };

    fetchData();
  }, []);

  const handleAddToCart = (userId, product) => {
    const prod = {userId, product}
    console.log(prod)
    const addToCart = async () => {
      try {
        const response = await addProdToCart(prod);
        // console.log(response);
      } catch (error) { }
    };

    addToCart();
  }

  return (
    <div className='list_prod'>
      <h5>The following table shows prices per 100g.</h5>
      <div className='list-group'>
        <div className='table-wrapper'>
          <table className='table table-hover table-default'>
            <thead>
              <tr>
                <th scope='col'>Name</th>
                <th scope='col'>Description</th>
                <th scope='col'>Price</th>
                <th scope='col'></th>
              </tr>
            </thead>
            <tbody>
              {products && products.map(product => {
                return (
                  <tr key={product.id}>
                    <td>{product.name}</td>
                    <td>{product.description}</td>
                    <td>{product.price}</td>
                    <td>
                      <div className='container_cart'>
                        <button className='buttonStyle add' onClick={() => handleAddToCart(userId, product.id) }>
                          <MdAdd size='1.5em'/>
                        </button>
                      </div>
                    </td>
                  </tr>
                )
              })}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  )
}

export default ProductList