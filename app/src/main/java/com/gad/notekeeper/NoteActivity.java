package com.gad.notekeeper;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class NoteActivity extends AppCompatActivity {

    // we added here "com.gad.notekeeper" to make our reference key unice
    public static final String NOTE_INFO = "com.gad.notekeeper.NOTE_INFO";
    Spinner spinnerCourses ;
    Toolbar toolbar ;
    NoteInfo mNote;
    EditText textNoteTitle, textNoteText;
    boolean NewNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        findViewById();
        spinnerCourses();
        readDisplayStateValues();
        // if it is not new note display the notes.
        if (!NewNote) {
        displayNotes(spinnerCourses,textNoteTitle,textNoteText);
        }
    }

    private void findViewById () {
        textNoteTitle = findViewById(R.id.text_note_title);
        textNoteText = findViewById(R.id.text_note_text);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void spinnerCourses () {
        // find id "Spinner"
        spinnerCourses = findViewById(R.id.spinner_courses);
        // make data list "that get the data from DataManager class"
        List<CourseInfo> listCourses = DataManager.getInstance().getCourses();
        // make an Adapter"ArrayAdapter" to Transfer the data to the spinner
        ArrayAdapter<CourseInfo> adapterCourses = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item, listCourses);
        // "Display the data when click on spinner *DropDown* " to display all the data in the Layout 2 in the spinner
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // display the data in the spinner
        spinnerCourses.setAdapter(adapterCourses);
    }

    /* Get the Information information from the last Activity to this Activity Methods */
    // pass the information from the last Activity to this Activity "to get it"
    private void readDisplayStateValues() {
        Intent intent = getIntent();
        // we will call the get method for etch variable from NoteInfo.class bellow
        // and we pass the KEY to get the values
        mNote = intent.getParcelableExtra(NOTE_INFO);
        // mNote null means blank note, so we can open EditNoteActivity to create new note.
        NewNote = (mNote == null);
    }

    private void displayNotes(Spinner spinnerCourses, EditText textNoteTitle, EditText textNoteText) {
        // get courses data list from DataManager class.
        List<CourseInfo> listCourses = DataManager.getInstance().getCourses();
        // "indexOf" to get the right course position in the spinner for etch Note,
        // mNote.getCourse(), to call the get method and get the object
        int courseIndex = listCourses.indexOf(mNote.getCourse());
        // after we got the right position, we fill it with the right course name (select it).
        spinnerCourses.setSelection(courseIndex);
        // fill the editText with the information
        textNoteTitle.setText(mNote.getTitle());
        textNoteText.setText(mNote.getText());
    }
    /* End of Methods */


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}