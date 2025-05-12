package com.example.app.factory;

public interface FormField {

    // Método que retorna el tipo del campo de formulario (por ejemplo: "text", "email", "password", etc.)
    String getType(); 

    // Método que retorna la etiqueta o nombre del campo de formulario, como el texto que se muestra al usuario
    // para que sepa qué ingresar (por ejemplo: "Username", "Password", etc.)
    String getLabel();

    // Método que indica si el campo de formulario es obligatorio o no. Retorna 'true' si es obligatorio,
    // de lo contrario, 'false'.
    boolean isRequired();
}
