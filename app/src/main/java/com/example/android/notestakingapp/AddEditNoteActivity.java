package com.example.android.notestakingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "com.example.android.notestakingapp.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.example.android.notestakingapp.EXTRA_TITLE";
        public static final String EXTRA_DESCRIPTION = "com.example.android.notestakingapp.EXTRA_DESCRIPTION";
    public static final String EXTRA_DATE = "com.example.android.notestakingapp.EXTRA_DATE";
    private EditText title_edit , description_edit;
    private TextView time;
    String currentDateandTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        time = findViewById(R.id.add_time);
        SimpleDateFormat sdf =   new SimpleDateFormat("EEE, d MMM yyyy HH:mm aaa");
        currentDateandTime = sdf.format(new Date());
        time.setText(currentDateandTime);

        title_edit = findViewById(R.id.edit_title);
        description_edit = findViewById(R.id.edit_description);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.close_white);
        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            title_edit.setText(intent.getStringExtra(EXTRA_TITLE));
            description_edit.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            time.setText(intent.getStringExtra(EXTRA_DATE));

        } else
        setTitle("Add Note");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_item,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void saveNote() {
        String title = title_edit.getText().toString();
        String description = description_edit.getText().toString();
        String date = currentDateandTime;

        if(title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this,"Either Title or Description or both are missing",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION,description);
        data.putExtra(EXTRA_DATE,date);

        int id = getIntent().getIntExtra(EXTRA_ID,-1);
        if(id != -1) {
            data.putExtra(EXTRA_ID,id);
        }

        setResult(RESULT_OK,data);
        finish();
    }
}