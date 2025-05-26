package com.example.app.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.*;

@Service
public class FormService {

    private final Firestore db; 
    private static final String COLLECTION_NAME = "products_registry";

    public FormService(FirebaseApp firebaseApp) {
        this.db = FirestoreClient.getFirestore(firebaseApp); 
    }

    public String saveProduct(Map<String, Object> product) throws ExecutionException, InterruptedException {

        String id = (String) product.get("id");
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Product ID is required");
        }

        ApiFuture<WriteResult> writeResult = db.collection(COLLECTION_NAME).document(id).set(product);
        writeResult.get(); // wait for operation
        return "Product saved successfully";
    }

    public List<Map<String, Object>> getAllProducts() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> querySnapshot = db.collection(COLLECTION_NAME).get();
        List<Map<String, Object>> products = new ArrayList<>();
        for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
            products.add(doc.getData());
        }
        return products;
    }

    public String updateProduct(String id, Map<String, Object> updatedProduct) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection(COLLECTION_NAME).document(id);
        DocumentSnapshot document = docRef.get().get();
        if (!document.exists()) {
            return null;
        }
        docRef.set(updatedProduct).get();
        return "Product updated successfully";
    }

    public String deleteProduct(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection(COLLECTION_NAME).document(id);
        DocumentSnapshot document = docRef.get().get();
        if (!document.exists()) {
            return null;
        }
        docRef.delete().get();
        return "Product deleted successfully";
    }

}

