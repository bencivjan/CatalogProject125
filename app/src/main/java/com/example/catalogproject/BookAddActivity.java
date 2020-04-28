package com.example.catalogproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.catalogproject.Logic.Book;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class BookAddActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_add);

        EditText titleEditText = findViewById(R.id.titleEditText);
        EditText authorEditText = findViewById(R.id.authorEditText);
        EditText genreEditText = findViewById(R.id.genreEditText);
        EditText descEditText = findViewById(R.id.descriptionEditText);
        Button addBookButton = findViewById(R.id.addBookButton);
        addBookButton.setOnClickListener(v -> {
            String title = titleEditText.getText().toString();
            String author = authorEditText.getText().toString();
            String genre = genreEditText.getText().toString().toLowerCase();
            String description = descEditText.getText().toString();
            // Would normally send add request to Kinvey here

            // Instead, start new book test
            try {
                JSONObject newJsonBook = new JSONObject();
                newJsonBook.put("title", title);
                newJsonBook.put("author", author);
                newJsonBook.put("genre", genre);
                newJsonBook.put("description", description);
                Book newBook = new Book(newJsonBook);
                ArrayList<Book> books = new ArrayList<>();
                books.add(newBook);
                Bundle bundle = new Bundle();
                bundle.putSerializable("books", books);
                Intent listIntent = new Intent(this, BookListActivity.class);
                listIntent.putExtras(bundle);
                startActivity(listIntent);
            } catch (JSONException ex) {
                Log.d("Bookie", "add book test failed");
            }
            // End new book test
        });
    }
}
