package com.ceatformacion.mascotaspsi.controller;


import com.ceatformacion.mascotaspsi.model.Mascotas;
import com.ceatformacion.mascotaspsi.repository.MascotasRepository;
import com.ceatformacion.mascotaspsi.services.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

//Sirve para generar ficheros JSON, PDF...
@RestController
//Clase que se encarga de manejar las peticiones de PDF
public class PdfController {
    // Llama al repositorio
    @Autowired
    MascotasRepository mascotasRepository;

    @Autowired
    private PdfService pdfService;

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> exportarPDF(){
        //Generar una lista
        List<Mascotas> mascotas = mascotasRepository.findAll();
        //Los flujos o datos se convierten en bytes, y lo que se va a generar
        ByteArrayInputStream pdfStream = pdfService.exportarMascotas(mascotas);
        //Se crean las cabeceras http para indicar que el archivo sera mostrado como pdf → navegador...
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=mascotas.pdf");
        //Se devuelve el flujo de bytes como respuesta
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(pdfStream.readAllBytes());
    }


}
