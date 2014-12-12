package com.cur.parcial3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cur.parcial3.com.dao.LibrosDao;
import com.cur.parcial3.com.dto.LibrosDto;

import java.util.ArrayList;


public class Registro extends ActionBarActivity {

    private EditText codigoIsb, nombreLibro, descripcion, genero, autor, editorial, nroPaginas;
    private Button btnAgregar, btnListar, btnConsultar, btnModificar, btnEliminar, btnCancelar;
    private int libroConsultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        codigoIsb = (EditText) findViewById(R.id.txtIsbn);
        nombreLibro = (EditText) findViewById(R.id.txtLibro);
        descripcion = (EditText) findViewById(R.id.txtDescripcion);
        genero = (EditText) findViewById(R.id.txtGenero);
        autor = (EditText) findViewById(R.id.txtAutor);
        editorial = (EditText) findViewById(R.id.txtEditorial);
        nroPaginas = (EditText) findViewById(R.id.txtPaginas);

        btnAgregar = (Button) findViewById(R.id.btnCreate);
        btnListar = (Button) findViewById(R.id.btnLista);
        btnConsultar = (Button) findViewById(R.id.btnConsulta);
        btnModificar = (Button) findViewById(R.id.btnUpdate);
        btnEliminar = (Button) findViewById(R.id.btnDelete);
        btnCancelar = (Button) findViewById(R.id.btnLimpiar);

        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                LibrosDao db = new LibrosDao(Registro.this);
                LibrosDto libro = new LibrosDto();
                libro.setCodigoIsbn(Integer.parseInt(codigoIsb.getText().toString()));
                libro.setNombreLibro(nombreLibro.getText().toString());
                libro.setDescripcion(descripcion.getText().toString());
                libro.setGenero(genero.getText().toString());
                libro.setAutor(autor.getText().toString());
                libro.setEditorial(editorial.getText().toString());
                libro.setNroPaginas(Integer.parseInt(nroPaginas.getText().toString()));
                db.agregarLibro(libro);
                Toast.makeText(Registro.this, "Libro " + libro.getNombreLibro() + " agregado :D", Toast.LENGTH_LONG).show();
            }
        });


        btnListar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                LibrosDao db = new LibrosDao(Registro.this);
                Intent intent = new Intent(Registro.this, LibrosDto.class);
                Bundle bundle = new Bundle();
                ArrayList<LibrosDto> libros = db.listarLibros();

                bundle.putSerializable("libros", libros);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btnConsultar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LibrosDao db = new LibrosDao(Registro.this);
                LibrosDto libro = new LibrosDto();

                libro = db.consultarLibro(Integer.parseInt(codigoIsb.getText().toString()));
                if (libro != null) {
                    libroConsultado = libro.getId();
                    nombreLibro.setText(libro.getNombreLibro());
                    descripcion.setText(libro.getDescripcion());
                    genero.setText(libro.getGenero());
                    autor.setText(libro.getAutor());
                    editorial.setText(libro.getEditorial());
                    nroPaginas.setText(libro.getNroPaginas());

                    btnModificar.setEnabled(true);
                    btnEliminar.setEnabled(true);
                }
            }
        });

        btnModificar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LibrosDao db = new LibrosDao(Registro.this);

                LibrosDto libro = new LibrosDto();
                libro.setId(libroConsultado);
                libro.setCodigoIsbn(Integer.parseInt(codigoIsb.getText().toString()));
                libro.setNombreLibro(nombreLibro.getText().toString());
                libro.setDescripcion(descripcion.getText().toString());
                libro.setGenero(genero.getText().toString());
                libro.setAutor(autor.getText().toString());
                libro.setEditorial(editorial.getText().toString());
                libro.setNroPaginas(Integer.parseInt(nroPaginas.getText().toString()));

                int e = db.actualizarLibro(libro);
                if (e != 0)
                    Toast.makeText(Registro.this, "Libro " + libro.getNombreLibro() + " modificado exitosamente", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Registro.this, "Estudiante " + libro.getNombreLibro() + " no se pudo modificar!", Toast.LENGTH_LONG).show();
                btnModificar.setEnabled(false);
                btnEliminar.setEnabled(false);
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                LibrosDao db = new LibrosDao(Registro.this);
                int e = db.elminarLibro(Integer.parseInt(codigoIsb.getText().toString()));
                if (e != 0)
                    Toast.makeText(Registro.this, "El libro fue eliminado", Toast.LENGTH_SHORT).show();
                btnEliminar.setEnabled(false);
                btnModificar.setEnabled(false);
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                codigoIsb.setText("");
                nombreLibro.setText("");
                descripcion.setText("");
                genero.setText("");
                autor.setText("");
                editorial.setText("");
                nroPaginas.setText("");
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registro, menu);
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
