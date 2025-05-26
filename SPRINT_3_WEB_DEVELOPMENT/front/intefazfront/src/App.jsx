//Router:	Envuelve la aplicación y gestiona la navegación.
//Routes:	Agrupa múltiples rutas y muestra solo la primera coincidencia.
//Route:	Define una URL específica y qué componente renderizar.

//Instala lo siguiente
//npm install sockjs-client stompjs


import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Navbar from "./pages/Navbar/Navbar";
import NewProducts from "./pages/mainpages/NewProducts";
import ManageProducts from "./pages/mainpages/ManageProducts";


function App() {
  return (
    <Router>
      <div className="app">
        <Navbar />
        <div className="content">
          <Routes>
            <Route path="/" element={<NewProducts />} />
            <Route path="/manage-products" element={<ManageProducts />} />
            <Route path="*" element={<NewProducts />} />
          </Routes>
        </div>
      </div>
    </Router>
  );
}

export default App;
