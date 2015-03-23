package com.example.alberto.practica_05;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity2 extends Activity {

    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);

        Intent i = getIntent();

        id = i.getLongExtra(MainActivity.db.DB_R_ID, -1);//si estás vacío ponle "-1"
        String Titulo = i.getStringExtra(MainActivity.db.DB_R_TITLE);
        String Nota = i.getStringExtra(MainActivity.db.DB_R_NOTE);

        EditText titulo_Cuadro = (EditText) findViewById(R.id.titulo);
        EditText nota_Cuadro = (EditText) findViewById(R.id.texto);

        titulo_Cuadro.setText(Titulo);
        nota_Cuadro.setText(Nota);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
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

    @Override
    protected void onPause(){
        super.onPause();

        EditText tituloC = (EditText) findViewById(R.id.titulo);
        EditText textoC = (EditText) findViewById(R.id.texto);

        String titulo = tituloC.getText().toString();
        String texto = textoC.getText().toString();

        if(titulo != "" || texto != ""){
            if (id == -1){
                MainActivity.db.noteAdd(titulo, texto);
            }
            else{
                MainActivity.db.noteUpdate(id, titulo, texto);
            }
        }

    }
}
