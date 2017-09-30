package com.example.root.workthread;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Spinner countriesSpinner;
    private Spinner optionsSpinner;
    private EditText etInitialYear;
    private EditText etFinalYear;
    private Button btGetData;
    private String[] countriesAbbr;
    private Button btShowGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniViews();
        countriesAbbr = getResources().getStringArray(R.array.countries_abbr);

        // TAG will be used to check whether the user can click on this button or not
        btShowGraph.setTag(0);
        btShowGraph.setBackgroundColor(getResources().getColor(R.color.button_unavailable));

        btGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btShowGraph.setTag(0);
                btShowGraph.setBackgroundColor(getResources().getColor(R.color.button_unavailable));
                InternetAsync internetAsync = new InternetAsync(getApplicationContext(), textView, btShowGraph);
                int pos = countriesSpinner.getSelectedItemPosition();
                int pos2 = optionsSpinner.getSelectedItemPosition();

                String finalYear = etFinalYear.getText().toString();
                String initialYear = etInitialYear.getText().toString();
                if(finalYear.equals("") || initialYear.equals("") ){
                    Toast.makeText(getBaseContext(),"Fill all fields", Toast.LENGTH_SHORT).show();
                }else if(Integer.parseInt(initialYear) > Integer.parseInt(finalYear)){
                    Toast.makeText(getBaseContext(),"Initial Year can't be greater than End Year",
                            Toast.LENGTH_SHORT).show();
                }else if(Integer.parseInt((finalYear)) > 2013){
                    Toast.makeText(getBaseContext(), "The api only supports query up until 2013",
                            Toast.LENGTH_SHORT).show();
                }else {
                    internetAsync.execute(countriesAbbr[pos],
                            (String) optionsSpinner.getItemAtPosition(pos2),
                            etInitialYear.getText().toString(),
                            etFinalYear.getText().toString());
                }
            }
        });

        btShowGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((int)btShowGraph.getTag() == 1) {
                    Intent in = new Intent(MainActivity.this, GraphActivity.class);
                    startActivity(in);
                } else {
                    Toast.makeText(MainActivity.this, "The data is not available yet. Please, wait a moment.", Toast.LENGTH_SHORT).show();
                }
                
            }
        });
    }

    public void iniViews(){
        textView = (TextView) findViewById(R.id.tvHere);
        countriesSpinner = (Spinner) findViewById(R.id.countries);
        optionsSpinner = (Spinner) findViewById(R.id.options);
        etInitialYear = (EditText) findViewById(R.id.etInitialYear);
        etFinalYear = (EditText) findViewById(R.id.etFinalYear);
        btGetData = (Button) findViewById(R.id.btGetData);
        btShowGraph = (Button) findViewById(R.id.btShowGraph);
    }
}
