import React, { useEffect, useState } from 'react';
import axios from 'axios';

function Dashbord() {
    const [products, setProducts] = useState([]);
    const [editingProductId, setEditingProductId] = useState(null);
    const [editedProduct, setEditedProduct] = useState({});

    const baseURL = 'http://localhost:8080'; // change if needed

    useEffect(() => {
        fetchProducts();
    }, []);

    const fetchProducts = async () => {
        try {
            const response = await axios.get(`${baseURL}/form/allProducts`);
            setProducts(response.data);
        } catch (error) {
            console.error('Error fetching products:', error);
        }
    };

    const handleEditClick = (product) => {
        setEditingProductId(product.id);
        setEditedProduct({ ...product });
    };

    const handleCancelEdit = () => {
        setEditingProductId(null);
        setEditedProduct({});
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setEditedProduct((prev) => ({ ...prev, [name]: value }));
    };

    const handleSave = async () => {
        try {
            await axios.put(`${baseURL}/form/${editedProduct.id}`, editedProduct);
            setEditingProductId(null);
            fetchProducts();
        } catch (error) {
            console.error('Error updating product:', error);
        }
    };

    const handleDelete = async (id) => {
        try {
            await axios.delete(`${baseURL}/form/${id}`);
            fetchProducts();
        } catch (error) {
            console.error('Error deleting product:', error);
        }
    };

    return (
        <div style={{ padding: '20px' }}>
            <h1>🛠️ Manage Products</h1>
            <table border="1" cellPadding="10" cellSpacing="0" style={{ width: '100%', marginTop: '20px' }}>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Category</th>
                        <th>Price</th>
                        <th>Stock</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {products.length === 0 ? (
                        <tr>
                            <td colSpan="6" align="center">No products found.</td>
                        </tr>
                    ) : (
                        products.map((product) => (
                            <tr key={product.id}>
                                {editingProductId === product.id ? (
                                    <>
                                        <td>{product.id}</td>
                                        <td>
                                            <input
                                                type="text"
                                                name="name"
                                                value={editedProduct.name}
                                                onChange={handleChange}
                                            />
                                        </td>
                                        <td>
                                            <input
                                                type="text"
                                                name="category"
                                                value={editedProduct.category}
                                                onChange={handleChange}
                                            />
                                        </td>
                                        <td>
                                            <input
                                                type="text"
                                                name="price"
                                                value={editedProduct.price}
                                                onChange={handleChange}
                                            />
                                        </td>
                                        <td>
                                            <input
                                                type="text"
                                                name="stock"
                                                value={editedProduct.stock}
                                                onChange={handleChange}
                                            />
                                        </td>
                                        <td>
                                            <button onClick={handleSave}>💾 Save</button>
                                            <button onClick={handleCancelEdit}>❌ Cancel</button>
                                        </td>
                                    </>
                                ) : (
                                    <>
                                        <td>{product.id}</td>
                                        <td>{product.name}</td>
                                        <td>{product.category}</td>
                                        <td>{product.price}</td>
                                        <td>{product.stock}</td>
                                        <td>
                                            <button onClick={() => handleEditClick(product)}>✏️ Edit</button>
                                            <button onClick={() => handleDelete(product.id)}>🗑️ Delete</button>
                                        </td>
                                    </>
                                )}
                            </tr>
                        ))
                    )}
                </tbody>
            </table>
        </div>
    );
}

export default Dashbord;

