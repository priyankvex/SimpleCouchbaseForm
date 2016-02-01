package com.wordpress.priyankvex.couchbasesimplesample;

import android.util.Log;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Document;

import java.util.Map;

/**
 * Created by priyank on 1/2/16.
 * This class contains all the functions required to perform database operations
 */
public class DatabaseHelper {

    public static void saveDocument(Map<String, Object> docContent){
        // create an empty document
        Document document = ApplicationController.getInstance().getDatabase().createDocument();
        // add content to document and write the document to the database
        try {
            document.putProperties(docContent);
            Log.d(Config.TAG, "Document written to database named " + Config.DATABASE_NAME + " with ID = " + document.getId());
        } catch (CouchbaseLiteException e) {
            Log.e(Config.TAG, "Cannot write document to database", e);
        }
    }

    public static Document readDocument(String docId){
        // retrieve the document from the database
        Document retrievedDocument = ApplicationController.getInstance().
                getDatabase()
                .getDocument(docId);
        // display the retrieved document
        Log.d(Config.TAG, "retrievedDocument=" + String.valueOf(retrievedDocument.getProperties()));
        return  retrievedDocument;
    }
}
