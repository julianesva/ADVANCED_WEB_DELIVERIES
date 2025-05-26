package com.example.app.controller;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.example.app.model.FormFactory;
import com.example.app.model.LoginRequest;
import com.example.app.service.FormService;
import com.example.app.factory.AdminFormFactory;
import com.example.app.factory.FormField;
import com.example.app.factory.GuestFormFactory;


@RestController
@RequestMapping("/form")
@CrossOrigin(origins = "http://localhost:5173")
public class FormController {


    private final FormService formService;

    public FormController(FormService formService) {
        this.formService = formService;
    }

    // Recibe un objeto JSON con la información de inicio de sesión (correo y contraseña)
    @PostMapping("/login")
        public Map<String, String> login(@RequestBody LoginRequest request) {

            // Se extraen las propiedades email y password del objeto LoginRequest
            String email = request.getEmail();
            String password = request.getPassword();

            if ("admin@email.com".equals(email) && "admin123".equals(password)) {
                return Map.of("role", "admin");
            } else if ("guest@email.com".equals(email) && "guest123".equals(password)) {
                return Map.of("role", "guest");
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas");
            }
        }



    // Este método maneja las solicitudes GET a la ruta /form/{role}, donde {role} es un parámetro en la URL
    @GetMapping("/{role}")
    public List<FormField> getForm(@PathVariable String role) {
        // Se crea una referencia a un objeto de tipo FormFactory, que será inicializado según el rol
        FormFactory factory;


        // Dependiendo del valor de 'role' en la URL, se inicializa la fábrica correspondiente
        switch (role.toLowerCase()) {
            case "admin":
                factory = new AdminFormFactory(); // Se crea una fábrica para formularios de administrador
                break;
            case "guest":
                factory = new GuestFormFactory();
                break;
            default:
                throw new IllegalArgumentException("Rol no válido");
        }

        // Finalmente, se retorna la lista de campos de formulario que la fábrica correspondiente genera
        return factory.createFormFields();
    }

    // Save product (Create or Update)
    @PostMapping("/save-product")
    public ResponseEntity<?> saveProduct(@RequestBody Map<String, Object> product) {
        try {
            String result = formService.saveProduct(product);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error saving product: " + e.getMessage());
        }
    }

    // Get all products
    @GetMapping("/allProducts")
    public ResponseEntity<?> getAllProducts() {
        try {
            List<Map<String, Object>> products = formService.getAllProducts();
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching products: " + e.getMessage());
        }
    }

    // Update product
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id, @RequestBody Map<String, Object> updatedProduct) {
        try {
            String result = formService.updateProduct(id, updatedProduct);
            if (result == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating product: " + e.getMessage());
        }
    }

    // Delete product
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        try {
            String result = formService.deleteProduct(id);
            if (result == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting product: " + e.getMessage());
        }
    }




}
