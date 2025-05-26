import { NavLink } from "react-router-dom";
import "./Navbar.css"; // Importa el CSS específico para Navbar

function Navbar() {
  return (
    <nav className="navbar">
      <div className="nav-container">
        <NavLink to="/new-product" className="nav-link">New Product</NavLink>
        <NavLink to="/manage-products" className="nav-link">Manage Products</NavLink>
      </div>
    </nav>
  );
}

export default Navbar;