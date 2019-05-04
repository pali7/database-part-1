package com.example.database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG= MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DbHelper dbHelper = new DbHelper(this);
        Notes note = dbHelper.getNote(1);
        Log.d(TAG, note.getTitle() + " : " + note.getDescription());
        dbHelper.insertNote(new Notes(1, "note1", "my 1st note"));
        Button button= findViewById(R.id.btnClick);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Notes> noteList=dbHelper.getAllNotes();
                for (Notes s: noteList){
                    Log.d(TAG, s.getTitle() + ":" + s.getDescription());
                }
            }
        });

    }
}
