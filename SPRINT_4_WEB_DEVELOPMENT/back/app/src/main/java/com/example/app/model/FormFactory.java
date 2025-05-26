package com.example.app.model;
import java.util.List;

import com.example.app.factory.FormField;

public interface FormFactory {

    // Este método debe devolver una lista de objetos de tipo FormField, que representarán los
    // campos del formulario. La lista de FormField creada puede variar dependiendo de la implementación
    // concreta de la fábrica (por ejemplo, una fábrica para formularios de "admin" o "guest").
    List<FormField> createFormFields();
}
