package com.example.root.workthread;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Spinner spinner;
    Spinner spinner2;
    EditText editText;
    EditText editText2;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniViews();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InternetAsync internetAsync = new InternetAsync(textView);
                int pos = spinner.getSelectedItemPosition();
                int pos2 = spinner2.getSelectedItemPosition();
                internetAsync.execute((String) spinner.getItemAtPosition(pos), (String) spinner2.getItemAtPosition(pos2), editText.getText().toString(), editText2.getText().toString());
            }
        });
    }

    public void iniViews(){
        textView = (TextView) findViewById(R.id.tvHere);
        spinner = (Spinner) findViewById(R.id.country);
        spinner2 = (Spinner) findViewById(R.id.topics);
        editText = (EditText) findViewById(R.id.InicialDate);
        editText2 = (EditText) findViewById(R.id.EndDate);
        btn1 = (Button) findViewById(R.id.btnGo);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
