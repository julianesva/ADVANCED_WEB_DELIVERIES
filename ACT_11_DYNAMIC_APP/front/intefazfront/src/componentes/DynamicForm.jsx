import { useState, useEffect } from "react";

function DynamicForm({ role }) {
  const [fields, setFields] = useState([]);

  useEffect(() => {
    fetch(`http://localhost:8080/form/${role}`)
      .then(res => res.json())
      .then(setFields);
  }, [role]);

  return (
    <form>
      {fields.map((field, index) => (
        <div key={index}>
          <label>{field.label}</label>
          <input
            type={field.type}
            required={field.required}
            name={field.label.toLowerCase()}
          />
        </div>
      ))}
      <button type="submit">Enviar</button>
    </form>
  );
}

export default DynamicForm;
