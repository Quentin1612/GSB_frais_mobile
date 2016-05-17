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
    RadioGroup BtnRadiosPF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        fillPFTextFields();
        fillCarbuTextFields();
        validation();
    }

    private void fillPFTextFields() {
        ArrayList<PuissanceFiscale> valeurs_PF = getPFValues();
        RadioButton radio1 = (RadioButton) findViewById(R.id.radio1);
        RadioButton radio2 = (RadioButton) findViewById(R.id.radio2);
        RadioButton radio3 = (RadioButton) findViewById(R.id.radio3);

        boolean radio1Set = false;
        boolean radio2Set = false;
        for(PuissanceFiscale pf : valeurs_PF) {
            if(!radio1Set) {
                radio1.setText(pf.getValeurPuissanceFiscale());
                radio1Set = true;
            } else if (!radio2Set) {
                radio2.setText(pf.getValeurPuissanceFiscale());
                radio2Set = true;
            } else {
                radio3.setText(pf.getValeurPuissanceFiscale());
            }
        }
    }

    private ArrayList<PuissanceFiscale> getPFValues() {
        ArrayList<PuissanceFiscale> resultats;
        Passerelle importDonnees = new Passerelle(this);
        resultats = importDonnees.getPuissancesFiscales();
        return resultats;
    }

    private void fillCarbuTextFields() {
        ArrayList<Carburant> libelles_carburant = getCarburantLib();
        RadioButton radio4 = (RadioButton) findViewById(R.id.radio4);
        RadioButton radio5 = (RadioButton) findViewById(R.id.radio5);

        boolean radio4Set = false;
        for(Carburant carburant : libelles_carburant) {
            if(!radio4Set) {
                radio4.setText(carburant.getLibelle_carburant());
                radio4Set = true;
            } else {
                radio5.setText(carburant.getLibelle_carburant());
            }
        }
    }

    private ArrayList<Carburant> getCarburantLib() {
        ArrayList<Carburant> resultats;
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

                RadioGroup BtnRadiosPF = (RadioGroup)findViewById(R.id.radioGroupPF);
                int selectedCVIndex = BtnRadiosPF.indexOfChild(findViewById(BtnRadiosPF.getCheckedRadioButtonId()));
                int CV = 0;
                switch(selectedCVIndex) {
                    case 0:
                        CV = 4;
                        break;
                    case 1:
                        CV = 5;
                        break;
                    case 2:
                        CV = 6;
                        break;
                }

                RadioGroup BtnRadiosCarburant = (RadioGroup)findViewById(R.id.radioGroupFuel);
                int selectedFuelIndex = BtnRadiosCarburant.indexOfChild(findViewById(BtnRadiosCarburant.getCheckedRadioButtonId()));
                String carburant = "";
                switch(selectedFuelIndex){
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