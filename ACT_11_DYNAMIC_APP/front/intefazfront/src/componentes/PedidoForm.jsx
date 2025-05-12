import React, { useState } from 'react';
import axios from 'axios';

const PedidoForm = () => {
  const [extras, setExtras] = useState({
    envio: false,
    regalo: false,
  });
  const [resultado, setResultado] = useState(null);

  const handleChange = (e) => {
    const { name, checked } = e.target;
    setExtras(prev => ({ ...prev, [name]: checked }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const seleccionados = Object.keys(extras).filter(key => extras[key]);

    try {
      const response = await axios.post('http://localhost:8080/api/pedidos/calcular', seleccionados);
      setResultado(response.data);
    } catch (error) {
      console.error('Error al calcular el pedido:', error);
    }
  };

  return (
    <div className="max-w-md mx-auto mt-10 p-6 border rounded shadow">
      <h2 className="text-xl font-bold mb-4">Calcular Pedido</h2>
      <form onSubmit={handleSubmit} className="space-y-4">
        <div>
          <label className="inline-flex items-center">
            <input
              type="checkbox"
              name="envio"
              checked={extras.envio}
              onChange={handleChange}
              className="mr-2"
            />
            Envío Rápido
          </label>
        </div>
        <div>
          <label className="inline-flex items-center">
            <input
              type="checkbox"
              name="regalo"
              checked={extras.regalo}
              onChange={handleChange}
              className="mr-2"
            />
            Envoltura de Regalo
          </label>
        </div>
        <button
          type="submit"
          className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
        >
          Calcular
        </button>
      </form>

      {resultado && (
        <div className="mt-6 p-4 border-t">
          <h3 className="font-semibold">Resultado:</h3>
          <p><strong>Descripción:</strong> {resultado.descripcion}</p>
          <p><strong>Costo:</strong> ${resultado.costo}</p>
        </div>
      )}
    </div>
  );
};

export default PedidoForm;
