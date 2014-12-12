package com.cur.parcial3;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.cur.parcial3.com.dto.LibrosDto;

import java.util.ArrayList;


public class Lista_Libros extends ActionBarActivity {

    private ListView lista;
    private Button btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_libros);

        lista = (ListView) findViewById(R.id.listLibros);
        btnRegresar = (Button) findViewById(R.id.btnBackP);

        ArrayList<LibrosDto> libros = (ArrayList<LibrosDto>) getIntent().getSerializableExtra("libros");
        ArrayAdapter<LibrosDto> adpLibro = new ArrayAdapter<LibrosDto>(this, android.R.layout.simple_list_item_1, libros);
        lista.setAdapter(adpLibro);

        btnRegresar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista__libros, menu);
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
