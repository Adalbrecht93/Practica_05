package com.example.alberto.practica_05;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener{

    public static baseDeDatos db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new baseDeDatos(this);

        Cursor c = db.noteRead();

        String[] fromColumns = {db.DB_TITLE, db.DB_NOTE};
        int[] toViews = {R.id.listTitulo, R.id.listTexto};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                R.layout.list_row,
                c,
                fromColumns,
                toViews,
                0);

        ListView l = (ListView) findViewById(R.id.lista);
        l.setAdapter(adapter);
        l.setOnItemClickListener(this);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent in = new Intent(this, MainActivity2.class);

        TextView CogeTituloBS = (TextView) view.findViewById(R.id.listTitulo);
        TextView CogeTextoBS = (TextView) view.findViewById(R.id.listTexto);

        String titulo = CogeTituloBS.getText().toString();
        String texto = CogeTextoBS.getText().toString();

        in.putExtra(db.DB_R_ID, id);
        in.putExtra(db.DB_R_TITLE, titulo);
        in.putExtra(db.DB_R_NOTE, texto);


        startActivity(in);
    }

    public void saltar_activity(View view){
        Intent in = new Intent(this, MainActivity2.class);
        startActivity(in);
    }
}
