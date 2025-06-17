package com.ceatformacion.mascotaspsi.services;

import com.ceatformacion.mascotaspsi.model.Mascotas;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PdfService {
    // 1. Crear un método que genere un PDF con la información de una lista <Mascotas>.
    public ByteArrayInputStream exportarMascotas(List<Mascotas> mascotas){
        // 2. Creamos el documento pdf con formato A4 horizontal.
        Document documento = new Document(PageSize.A4.rotate());
        // Indicamos donde guardará (temporalmente) el flujo de la tabla generada
        ByteArrayOutputStream salida = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(documento, salida);
            // 4. Abrimos el documento para comenzar a escribir.
            documento.open();
            // Definimos las fuentes que desamos
            Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
            // Crear el título del documento
            Paragraph titulo = new Paragraph("Listado de Mascotas", tituloFont);
            // Centramos el título al centro
            titulo.setAlignment(Element.ALIGN_CENTER);
            // Añadir el título al documento
            documento.add(titulo);
            // Añadir una línea en blanco, debajo del título
            documento.add(Chunk.NEWLINE);

            //Crear una tabla con los campos que vamos a mostrar
            PdfPTable tabla = new PdfPTable(6);
            // Establecer el ancho de la tabla en 100% (completo)
            tabla.setWidthPercentage(100);
            // Define el ancho de cada columna
            tabla.setWidths(new float[]{3, 3, 3, 3, 3, 3});
            //Definimos la fuente del título de la tabla
            Font tituloTabla = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);

            //Añadir los encabezados a la tabla
            tabla.addCell(new PdfPCell(new Phrase("Especie", tituloTabla)));
            tabla.addCell(new PdfPCell(new Phrase("Especie", tituloTabla)));
            tabla.addCell(new PdfPCell(new Phrase("Raza", tituloTabla)));
            tabla.addCell(new PdfPCell(new Phrase("Edad", tituloTabla)));
            tabla.addCell(new PdfPCell(new Phrase("Peso", tituloTabla)));
            tabla.addCell(new PdfPCell(new Phrase("DNI Propietario", tituloTabla)));

            // Añadir el contenido recorriendo un Array

            //Añadir estas celdas al documento
            documento.add(tabla);


        }catch (Exception e){

        }
        return new ByteArrayInputStream(salida.toByteArray());
    }

}