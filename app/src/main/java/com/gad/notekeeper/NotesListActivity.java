package com.gad.notekeeper;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NotesListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // make new note when click on Floating Button
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentStartActivity = new Intent(NotesListActivity.this, NoteActivity.class);
                startActivity(intentStartActivity);
            }
        });

        InitializeDisplayContent();
    }

    private void InitializeDisplayContent () {
        // we make it final to can call it in setOnItemClickListener method "because it is anonymous class"
        final ListView listNotes = findViewById(R.id.notes);
        List<NoteInfo> note = new ArrayList<>(DataManager.getInstance().getNotes());
        ArrayAdapter<NoteInfo> adapterNotes = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,note);
        listNotes.setAdapter(adapterNotes);

        // call setOnItemClickListener to open EditNoteActivity when the user click on every item in the list
        listNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(NotesListActivity.this, NoteActivity.class);
                // to get the position for every item in the lisView
                NoteInfo note = (NoteInfo) listNotes.getItemAtPosition(position);
                // pass the position and notInfo variable to can send the note info to the other activity
                // NOTE_INFO is a key that we will send with it our objects variables in NoteInfo.class
                intent.putExtra(NoteActivity.NOTE_INFO, note);
                startActivity(intent);
            }
        });

    }
}