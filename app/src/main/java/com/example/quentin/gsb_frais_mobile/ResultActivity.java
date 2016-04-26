package com.example.quentin.gsb_frais_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    Button buttonRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button buttonRetour = (Button) findViewById(R.id.returnButton);
        buttonRetour.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        fillOutputFields();
    }

    private void fillOutputFields(){
        TextView outputNbKm = (TextView) findViewById(R.id.textViewNbKm);
        TextView outputCV = (TextView) findViewById(R.id.textViewCV);
        TextView outputCarburant = (TextView) findViewById(R.id.textViewCarburant);
        TextView outputFrais = (TextView) findViewById(R.id.textViewFrais);

        Bundle bu = getIntent().getExtras();
        int valueKm = bu.getInt("nbrKm");
        int valueCV = bu.getInt("nbrCV");
        String valeurCarburant = bu.getString("carburantChoisi");

        double montantFrais = calculFrais(valueKm, valueCV, valeurCarburant);

        outputNbKm.setText("Kilomètres renseignés : " + valueKm + " km");
        outputCV.setText("Puissance fiscale du véhicule renseignée : " + valueCV + " cv");
        outputCarburant.setText("Carburant renseigné : " + valeurCarburant);
        outputFrais.setText("Montant des frais kilométriques : " + montantFrais + " €");
    }

    private double calculFrais(int kmValue, int cvValue, String valeurCarburant) {
        double montantFrais = kmValue;
        if (valeurCarburant.equals("Diesel")) {
            switch (cvValue) {
                case 4:
                    montantFrais *= 0.52;
                    break;
                case 5:
                    montantFrais *= 0.58;
                    break;
                case 6:
                    montantFrais *= 0.58;
                    break;
                default:
                    break;
            }
        } else {
            if(valeurCarburant.equals("Essence")){
                switch (cvValue) {
                    case 4:
                        montantFrais *= 0.62;
                        break;
                    case 5:
                        montantFrais *= 0.68;
                        break;
                    case 6:
                        montantFrais *= 0.68;
                        break;
                    default:
                        break;
                }
            }
        }
        return montantFrais;
    }
}
