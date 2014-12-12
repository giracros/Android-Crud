package com.cur.parcial3.com.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.cur.parcial3.com.dto.LibrosDto;

import java.util.ArrayList;

/**
 * Nombre del paquete com.cur.parcial3.com.dao
 * Creado por danielfuentes en la fecha 12/11/14.
 * Todos los derechos reservados
 * Email: giracros@gmail.com
 * Mobile: 3013366588
 * Nombre del proyecto Parcial3.
 */

public class LibrosDao extends SQLiteOpenHelper {

    private static final int VERSION_DB = 1;
    private static final String NOMBRE_DB = "BibliotecaDB";

    public LibrosDao(Context context) {
        super(context, NOMBRE_DB, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE libro (" + "id INTEGER PRIMARY KEY AUTOINCREMENT," + "codigoIsbn INTEGER(10,0) NOT NULL," + "nombreLibro TEXT(50,0) NOT NULL," + "descripcion TEXT(300,0) NOT NULL," + "autor TEXT(50,0) NOT NULL," + "genero  TEXT(50,0) NOT NULL, " + "editorial  TEXT(50,0) NOT NULL, " + "nroPaginas INTEGER(4,0) NOT NULL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        String sql = "DROP TABLE IF EXIST libro";
        db.execSQL(sql);
        this.onCreate(db);
    }

    public void agregarLibro(LibrosDto libro) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("codigoIsbn", libro.getCodigoIsbn());
        values.put("nombreLibro", libro.getNombreLibro());
        values.put("descripcion", libro.getDescripcion());
        values.put("autor", libro.getAutor());
        values.put("genero", libro.getGenero());
        values.put("editorial", libro.getEditorial());
        values.put("nroPaginas", libro.getNroPaginas());

        db.insert("libro", null, values);
        Log.d("agregarLibro", libro.toString());
        db.close();
    }

    public ArrayList<LibrosDto> listarLibros() {

        ArrayList<LibrosDto> libros = new ArrayList<LibrosDto>();
        String sql = "SELECT * FROM libro";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        LibrosDto libro = null;

        if (cursor.moveToFirst()) {
            do {
                libro = new LibrosDto();
                libro.setId(Integer.parseInt(cursor.getString(0)));
                libro.setCodigoIsbn(cursor.getInt(1));
                libro.setNombreLibro(cursor.getString(2));
                libro.setDescripcion(cursor.getString(3));
                libro.setAutor(cursor.getString(4));
                libro.setGenero(cursor.getString(5));
                libro.setEditorial(cursor.getString(6));
                libro.setNroPaginas(cursor.getInt(7));
                libros.add(libro);
            } while (cursor.moveToNext());
        }
        return libros;
    }

    public LibrosDto consultarLibro(int isbn) {
        String columns[] =

                {"id",
                        "codigoIsbn",
                        "nombreLibro",
                        "descripcion",
                        "autor",
                        "genero",
                        "editorial",
                        "nroPaginas"};

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("libro", columns, "codigoIsbn = ?",
                new String[]{String.valueOf(isbn)}, null, null, null, null);
        if (cursor != null) {

            cursor.moveToFirst();

            LibrosDto libro = new LibrosDto();

            libro.setId(Integer.parseInt(cursor.getString(0)));
            libro.setCodigoIsbn(cursor.getInt(1));
            libro.setNombreLibro(cursor.getString(2));
            libro.setDescripcion(cursor.getString(3));
            libro.setAutor(cursor.getString(4));
            libro.setGenero(cursor.getString(5));
            libro.setEditorial(cursor.getString(6));
            libro.setNroPaginas(cursor.getInt(7));

            return libro;
        } else return null;
    }


    public int elminarLibro(int isbn) {

        SQLiteDatabase db = this.getWritableDatabase();

        int e = db.delete("libro", "codigoIsbn = ?", new String[]{String.valueOf(isbn)});
        db.close();
        return e;
    }

    public int actualizarLibro(LibrosDto libro) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("codigoIsbn", libro.getCodigoIsbn());
        values.put("nombreLibro", libro.getNombreLibro());
        values.put("descripcion", libro.getDescripcion());
        values.put("autor", libro.getAutor());
        values.put("genero", libro.getGenero());
        values.put("editorial", libro.getEditorial());
        values.put("nroPaginas", libro.getNroPaginas());

        int e = db.update("libro", values,
                "id = ?", new String[]{String.valueOf(libro.getId())});
        db.close();
        return e;
    }
}
