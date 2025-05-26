import { useState } from "react";
import axios from "axios";
import LoginForm from "../../componentes/LoginForm";

function NewProducts() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [role, setRole] = useState("");
  const [formFields, setFormFields] = useState([]);
  const [formData, setFormData] = useState({});

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };             

  const handleSubmit = async (e) => {
    e.preventDefault(); // Prevent default page reload
  
    try {
      const response = await axios.post("http://localhost:8080/form/save-product", formData);
      console.log("Response:", response.data);
      setFormData({});
    } catch (error) {
      console.error("Error submitting form:", error);
    }
  };
  
  const handleLoginSuccess = async (userRole) => {
    setIsLoggedIn(true);
    setRole(userRole);

    try {
      const response = await axios.get(`http://localhost:8080/form/${userRole}`);
      setFormFields(response.data);
    } catch (err) {
      console.error("Error when the code is trying to get the form:", err);
    }
  };

  return (
    <div className="p-6 max-w-md mx-auto">
      {!isLoggedIn ? (
        <LoginForm onLoginSuccess={handleLoginSuccess} />
      ) : (
        <div>
          {role === 'admin' ? (
            <div>
              <h2 className="text-xl mb-4">Add New Products In Stock: Admin</h2>
            <form onSubmit={handleSubmit}>
              {formFields.map((field, index) => (
                <div key={index} className="mb-4">
                  <label className="block mb-1">{field.label}</label>
                  <input
                    name={field.label.toLowerCase().replace(/\s+/g, "_")}
                    type={field.type}
                    required={field.required}
                    value={formData[field.label.toLowerCase().replace(/\s+/g, "_")] || ""}
                    className="w-full border p-2"
                    onChange={handleInputChange}
                  />
                </div>
              ))}
              <button type="submit" className="bg-green-600 text-white px-4 py-2 rounded">
                Submit
              </button>
            </form>
            </div>
          ) : (
            <h2 className="text-red-600">
              As "{role}" you do not have permissions to add new products
            </h2>
          )}
        </div>
      )}
    </div>
  );
}

export default NewProducts;
