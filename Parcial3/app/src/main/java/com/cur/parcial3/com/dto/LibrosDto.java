package com.cur.parcial3.com.dto;

/**
 * Nombre del paquete com.cur.parcial3
 * Creado por danielfuentes en la fecha 12/11/14.
 * Todos los derechos reservados
 * Email: giracros@gmail.com
 * Mobile: 3013366588
 * Nombre del proyecto Parcial3.
 */
public class LibrosDto {
    private int id;
    private int codigoIsbn;
    private int nroPaginas;
    private String nombreLibro;
    private String descripcion;
    private String autor;
    private String genero;
    private String editorial;

    public LibrosDto() {
    }

    public LibrosDto(int codigoIsbn,
                     String nombreLibro,
                     String descripcion,
                     String autor,
                     String genero,
                     String editorial,
                     int nroPaginas) {
        this.codigoIsbn = codigoIsbn;
        this.nombreLibro = nombreLibro;
        this.descripcion = descripcion;
        this.autor = autor;
        this.genero = genero;
        this.editorial = editorial;
        this.nroPaginas = nroPaginas;
    }


    public int getCodigoIsbn() {
        return codigoIsbn;
    }

    public void setCodigoIsbn(int codigoIsbn) {
        this.codigoIsbn = codigoIsbn;
    }

    public int getNroPaginas() {
        return nroPaginas;
    }

    public void setNroPaginas(int nroPaginas) {
        this.nroPaginas = nroPaginas;
    }

    public String getNombreLibro() {
        return nombreLibro;
    }

    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    @Override
    public String toString() {
        return this.nombreLibro + ",  ISBN " + this.codigoIsbn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
