package com.xea.whatsappxea.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.xea.whatsappxea.FirebaseDB.FirebaseDB;
import com.xea.whatsappxea.R;
import com.xea.whatsappxea.models.Conversacion;
import com.xea.whatsappxea.models.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class GroupActivity extends AppCompatActivity {
    FirebaseFirestore db;
    List<String> usersStr;
    Spinner spinner;
    TextView nums;
    TextView nombreG;
    TextView fotoG;
    Button aceptar;
    Button cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        String userLogged = (String) getIntent().getStringExtra("userLogged");
        db = FirebaseDB.getInstance();
        spinner = (Spinner)  findViewById(R.id.spinner);
        nums = (TextView)  findViewById(R.id.txtNums);
        fotoG = (TextView)  findViewById(R.id.txtFotoGrupo);
        nombreG = (TextView)  findViewById(R.id.txtNombreGrupo);

        aceptar = (Button) findViewById(R.id.btnAceptar);
        cancelar = (Button) findViewById(R.id.btnCancelar);

        Query usersRef = db.collection("users").whereNotEqualTo("telNumber",userLogged);
        usersRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        usersStr = new ArrayList<>();
                        usersStr.add("");
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            User user = documentSnapshot.toObject(User.class);
                            usersStr.add(user.getTelNumber());
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(GroupActivity.this, android.R.layout.simple_spinner_item, usersStr);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(adapter);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(GroupActivity.this, "Error al obtener datos", Toast.LENGTH_SHORT).show();
                    }
                });

        List<String> txtOut = new ArrayList<>();
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String num = spinner.getSelectedItem().toString();
                    if (txtOut.contains(num)&&num!=""){
                        txtOut.remove(num);
                    }else if(num!=""){
                        txtOut.add(num);
                    }

                     String out = "";

                    for(String s: txtOut){
                        out+=s+"\n";
                    }


                    nums.setText(out);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

          aceptar.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  if(nombreG.equals("")||nums.equals("")){
                      Toast.makeText(GroupActivity.this, "Deve elegir participantes al grupo y darle un nombre", Toast.LENGTH_SHORT).show();
                  }else{
                      txtOut.add(userLogged);
                      Conversacion c = new Conversacion( txtOut,nombreG.getText().toString(),R.drawable.people);
                      CollectionReference conversacionesRef = db.collection("conversaciones");
                      conversacionesRef.add(c);

                      Intent intent = new Intent(GroupActivity.this, MainActivity.class);
                      intent.putExtra("userLogged",userLogged);
                      startActivity(intent);
                  }
              }
          });


          cancelar.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent intent = new Intent(GroupActivity.this, MainActivity.class);
                  intent.putExtra("userLogged",userLogged);
                  startActivity(intent);
              }
          });
    }

}