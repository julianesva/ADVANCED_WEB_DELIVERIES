package com.example.app.factory;

import com.example.app.model.FormFactory;
import com.example.app.model.GenericField;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import java.util.ArrayList;
import java.util.List;

/**
* GuestFormFactory es una implementación de la interfaz FormFactory.
* Su responsabilidad es generar una lista de campos de formulario (FormField) para usuarios tipo "Guest".
* Esta implementación recupera los campos desde una colección llamada "formFields" en Firestore.
*/
public class GuestFormFactory implements FormFactory {

/**
* Crea una lista de campos de formulario dinámicamente a partir de Firestore.
*
* @return Lista de objetos FormField, representando los campos que se deben mostrar en el formulario.
*/
@Override
public List<FormField> createFormFields() {
// Lista que contendrá los campos del formulario
List<FormField> fields = new ArrayList<>();

try {
// Obtiene la instancia de Firestore
Firestore db = FirestoreClient.getFirestore();

// Realiza la consulta para obtener todos los documentos de la colección "formFields"
ApiFuture<QuerySnapshot> query = db.collection("guest_acess").get();
List<QueryDocumentSnapshot> documents = query.get().getDocuments();

// Recorre cada documento recuperado de Firestore
for (QueryDocumentSnapshot doc : documents) {
// Obtiene los valores de cada campo desde Firestore
String type = doc.getString("type");
String label = doc.getString("label");
Boolean required = doc.getBoolean("required");

// Imprime los datos del campo para depuración
System.out.println("-----" + type + "---" + label + "----" + required);

// Crea un nuevo campo genérico y lo añade a la lista
fields.add(new GenericField(type, label, required != null && required));
// Nota: Se almacena como FormField por el uso de polimorfismo
}

// Punto de control para saber que se llegó al final sin errores
System.out.println("Todo Correcto");

} catch (Exception e) {
// Mensaje de error personalizado para depuración
System.out.println("Algun error acontecio!");
e.printStackTrace(); // Imprime el stack trace para identificar el error
}

// Devuelve la lista de campos generados
return fields;
}
}
