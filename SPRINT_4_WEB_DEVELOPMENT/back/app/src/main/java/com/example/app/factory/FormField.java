package com.example.app.factory;

public abstract class FormField {
    // Atributos de la clase que representan las propiedades del campo del formulario:
    protected final String type;    
    protected final String label;    
    protected final boolean required; 

    // Constructor que inicializa los atributos de la clase con los valores proporcionados al crear un objeto
    protected FormField(String type, String label, boolean required) {
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
