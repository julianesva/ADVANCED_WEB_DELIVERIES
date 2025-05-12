package com.example.app.model;

import com.example.app.factory.FormField;

// La clase GenericField implementa la interfaz FormField. Esta clase representa un campo genérico
public class GenericField implements FormField {
    
    // Atributos de la clase que representan las propiedades del campo del formulario:
    private final String type;    
    private final String label;    
    private final boolean required; 

    // Constructor que inicializa los atributos de la clase con los valores proporcionados al crear un objeto
    public GenericField(String type, String label, boolean required) {
        this.type = type;     
        this.label = label; 
        this.required = required; 
    }

    // Método para obtener el tipo del campo de formulario.
    public String getType() { 
        return type; 
    }

    // Método para obtener la etiqueta del campo de formulario.
    public String getLabel() { 
        return label; 
    }

    // Método para verificar si el campo es obligatorio o no.
    public boolean isRequired() { 
        return required; 
    }
}

