package com.example.quentin.gsb_frais_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button leBtnValider;
    RadioGroup BtnRadiosCarburant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("mesmessages", "debut");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("mesmessages", "debut2");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        leBtnValider = (Button)findViewById(R.id.buttonValider);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ArrayList<Carburant> libelles_carburant = getCarburantLib();
        RadioButton radio1 = (RadioButton) findViewById(R.id.radio1);
        RadioButton radio2 = (RadioButton) findViewById(R.id.radio2);

        boolean radio1Done = false;
        for(Carburant carburant : libelles_carburant) {
            if(!radio1Done) {
                radio1.setText(carburant.getLibelle_carburant());
                radio1Done = true;
            } else {
                radio2.setText(carburant.getLibelle_carburant());
            }
        }

        validation();
    }

    private ArrayList<Carburant> getCarburantLib() {
        ArrayList<Carburant> resultats = new ArrayList<Carburant>();
        Passerelle importDonnees = new Passerelle(this);
        resultats = importDonnees.getCarburants();
        return resultats;
    }

    private void validation() {
        leBtnValider.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText leNbDeKm = (EditText)findViewById(R.id.editText_nbKm);
                int nbKm = Integer.parseInt(leNbDeKm.getText().toString());
                // Log.d("mesMessages",""+nbKm);

                EditText leNbDeCV = (EditText)findViewById(R.id.editText_cv);
                int CV = Integer.parseInt(leNbDeCV.getText().toString());

                RadioGroup BtnRadiosCarburant = (RadioGroup)findViewById(R.id.radioGroupFuel);
                int selectedIndex = BtnRadiosCarburant.indexOfChild(findViewById(BtnRadiosCarburant.getCheckedRadioButtonId()));

                String carburant = "";
                switch(selectedIndex){
                    case -1:
                        carburant = "Aucun carburant saisi!";
                        break;
                    case 0:
                        carburant = "Essence";
                        break;
                    case 1:
                        carburant = "Diesel";
                        break;
                }

                Intent i = new Intent(getApplicationContext(), ResultActivity.class);
                i.putExtra("nbrKm", nbKm);
                i.putExtra("nbrCV", CV);
                i.putExtra("carburantChoisi", carburant);
                startActivity(i);
                finish();
            }
        });
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