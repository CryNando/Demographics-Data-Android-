package com.example.root.workthread;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Spinner countriesSpinner;
    private Spinner optionsSpinner;
    private EditText etInitialYear;
    private EditText etFinalYear;
    private Button btGetData;
    private String[] countriesAbbr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniViews();
        countriesAbbr = getResources().getStringArray(R.array.countries_abbr);

        btGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InternetAsync internetAsync = new InternetAsync(textView);
                int pos = countriesSpinner.getSelectedItemPosition();
                int pos2 = optionsSpinner.getSelectedItemPosition();
                internetAsync.execute(countriesAbbr[pos],
                        (String) optionsSpinner.getItemAtPosition(pos2),
                        etInitialYear.getText().toString(),
                        etFinalYear.getText().toString());
            }
        });
    }

    public void iniViews(){
        textView = (TextView) findViewById(R.id.tvHere);
        countriesSpinner = (Spinner) findViewById(R.id.countries);
        optionsSpinner = (Spinner) findViewById(R.id.options);
        etInitialYear = (EditText) findViewById(R.id.etInitialYear);
        etFinalYear = (EditText) findViewById(R.id.etFinalYear);
        btGetData = (Button) findViewById(R.id.btnGo);

    }
}
