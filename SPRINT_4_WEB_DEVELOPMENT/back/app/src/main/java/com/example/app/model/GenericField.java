package com.example.app.model;

import com.example.app.factory.FormField;

// La clase GenericField implementa la interfaz FormField. Esta clase representa un campo genérico
public class GenericField extends FormField {

    public GenericField(String type, String label, boolean required) {
        super(type, label, required);
    }
    

}

