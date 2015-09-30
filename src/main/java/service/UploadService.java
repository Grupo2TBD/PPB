/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ejb.UploadEJBLocal;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.Camara;
import model.PermisoFotografia;
import model.Privacidad;
import model.Tag;
import model.Usuario;
import com.sun.jersey.multipart.FormDataParam;
import com.sun.jersey.core.header.FormDataContentDisposition;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author ian
 */
@Path("/upload")
public class UploadService {
    @EJB
    UploadEJBLocal uploadEJB;
    
 

    @GET
    @Path("/subirFoto")
    @Produces({"application/json"})
    public String uploadService(){
        Usuario user = new Usuario();
        user.setIdUser(1);
        Privacidad privacidad = new Privacidad();
        privacidad.setIdPrivacidad(1);
        PermisoFotografia permisoFoto = new PermisoFotografia();
        permisoFoto.setIdPermisoFotografia(1);
        Camara camara = new Camara();
        camara.setIdCamara(1);
        String titulo = "Test titulo";
        String descripcion = "Test descripcion";
        List<Tag> tags = new ArrayList();
        /*Tag tag1 = new Tag();
        tag1.setIdTag(3);
        tag1.setNombreTag("Tag 5");
        Tag tag2 = new Tag();
        tag2.setIdTag(4);
        tag2.setNombreTag("Tag 7");
        tags.add(tag1);
        tags.add(tag2);*/
        List<Usuario> etiqueta = new ArrayList();
        Usuario etiqueta1 = new Usuario();
        etiqueta1.setIdUser(2);
        etiqueta.add(etiqueta1);
        
        return uploadEJB.insertPhotoInfo(user, camara, privacidad, permisoFoto, tags, etiqueta, titulo, descripcion);
    }
    
}