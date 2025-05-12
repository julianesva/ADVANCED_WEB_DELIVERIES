import { useState } from "react";
import axios from "axios";
import LoginForm from "../../componentes/LoginForm";

function About() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [role, setRole] = useState("");
  const [formFields, setFormFields] = useState([]);

  const handleLoginSuccess = async (userRole) => {
    setIsLoggedIn(true);
    setRole(userRole);

    try {
      const response = await axios.get(`http://localhost:8080/form/${userRole}`);
      setFormFields(response.data);
    } catch (err) {
      console.error("Error al obtener el formulario:", err);
    }
  };

  return (
    <div className="p-6 max-w-md mx-auto">
      {!isLoggedIn ? (
        <LoginForm onLoginSuccess={handleLoginSuccess} />
      ) : (
        <div>
          <h2 className="text-xl mb-4">Formulario para rol: {role}</h2>
          <form>
            {formFields.map((field, index) => (
              <div key={index} className="mb-4">
                <label className="block mb-1">{field.label}</label>
                <input
                  type={field.type}
                  required={field.required}
                  className="w-full border p-2"
                />
              </div>
            ))}
            <button type="submit" className="bg-green-600 text-white px-4 py-2 rounded">
              Enviar
            </button>
          </form>
        </div>
      )}
    </div>
  );
}

export default About;
