package com.example.netactivity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;

public class NetDetail extends Activity {

    final int COUNTRY1=1;
    final int COUNTRY2=2;

    TextView txtZeme;
    TextView txtZeme2;
    TextView txtMenaKod;
    TextView txtMenaKod2;
    TextView txtKurz;
    TextView txtKurz2;
    ImageView imageFlag;
    ImageView imageFlag2;

    EditText editHodnota;
    EditText editHodnota2;
    boolean automaticChanged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_detail);

        txtZeme = (TextView)findViewById(R.id.txtZeme);
        txtZeme2 = (TextView)findViewById(R.id.txtZeme2);
        txtMenaKod = (TextView)findViewById(R.id.txtKod);
        txtMenaKod2 = (TextView)findViewById(R.id.txtKod2);
        txtKurz = (TextView)findViewById(R.id.txtKurz);
        txtKurz2 = (TextView)findViewById(R.id.txtKurz2);
        imageFlag = (ImageView)findViewById(R.id.imageFlag);
        imageFlag2 = (ImageView)findViewById(R.id.imageFlag2);


        txtZeme.setText("Česka republika");
        txtZeme2.setText("Česka republika");
        txtMenaKod.setText("CZK");
        txtMenaKod2.setText("CZK");
        txtKurz.setText("1.00");
        txtKurz2.setText("1.00");


        editHodnota = (EditText) findViewById(R.id.editHodnota);
        editHodnota2 = (EditText) findViewById(R.id.editHodnota2);



        TextWatcher ed = new TextWatcher() {

            public void afterTextChanged(Editable s) {
                if (!automaticChanged) {
                    automaticChanged=true;
                    String strHodnota = s.toString();

                    if (strHodnota.equals("")) {
                        strHodnota = "1";
                        editHodnota.setText(strHodnota);
                    }

                    double kurz = Double.parseDouble((String) txtKurz.getText());
                    double kurz2 = Double.parseDouble((String) txtKurz2.getText());
                    double hodnota = Double.parseDouble(strHodnota);

                    double vysledek = hodnota * (kurz / kurz2);

                    editHodnota2.setText(String.format("%.2f", vysledek));

                }
                else {
                    automaticChanged=false;
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        };

        editHodnota.addTextChangedListener(ed);


        TextWatcher ed2 = new TextWatcher() {

            public void afterTextChanged(Editable s) {
                if (!automaticChanged) {
                    automaticChanged=true;
                    String strHodnota = s.toString();
                    if (strHodnota.equals("")) {
                        strHodnota = "1";
                        editHodnota2.setText(strHodnota);
                    }
                    double kurz = Double.parseDouble((String) txtKurz2.getText());
                    double kurz2 = Double.parseDouble((String) txtKurz.getText());
                    double hodnota = Double.parseDouble(strHodnota);

                    double vysledek = hodnota * (kurz / kurz2);
                    editHodnota.setText(String.format("%.2f", vysledek));
                }
                else {
                    automaticChanged=false;
                }

            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        };

        editHodnota2.addTextChangedListener(ed2);


        LinearLayout country1 = (LinearLayout)findViewById(R.id.linearLayout);

        country1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(NetDetail.this, NetworkActivity.class),COUNTRY1);
            }
        });

        LinearLayout country2 = (LinearLayout)findViewById(R.id.linearLayout2);

        country2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(NetDetail.this, NetworkActivity.class), COUNTRY2);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("onActivityResult", "OK");
        if (requestCode == COUNTRY1) {
            if (resultCode == RESULT_OK) {
                String kod = (String) data.getExtras().getString("selectedKod");
                txtZeme.setText((String) data.getExtras().getString("selectedZeme"));
                txtMenaKod.setText((String) data.getExtras().getString("selectedMena"));
                txtKurz.setText(Double.toString(data.getExtras().getDouble("selectedKurz")));
                imageFlag.setImageResource(getResources().getIdentifier("drawable/flag_" + kod.toLowerCase() , "drawable", getPackageName()));
            }
        }
        if (requestCode == COUNTRY2) {
            if (resultCode == RESULT_OK) {
                String kod = (String) data.getExtras().getString("selectedKod");
                txtZeme2.setText((String) data.getExtras().getString("selectedZeme"));
                txtMenaKod2.setText((String) data.getExtras().getString("selectedMena"));
                txtKurz2.setText(Double.toString(data.getExtras().getDouble("selectedKurz")));
                imageFlag2.setImageResource(getResources().getIdentifier("drawable/flag_" + kod.toLowerCase() , "drawable", getPackageName()));

            }
        }
    }

}
