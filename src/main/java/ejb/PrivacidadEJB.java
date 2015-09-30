/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;


import facade.PrivacidadEJBFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import model.Privacidad;

/**
 *
 * @author sebastian
 */
@Stateless 
public class PrivacidadEJB implements PrivacidadEJBLocal{
    
    @EJB
    PrivacidadEJBFacade privacidadFacade;
    
    public PrivacidadEJB(){
    
    }
    
    @Override
    public Privacidad obtenerPrivacidad(int idPrivacidad)
    {
        Object id = idPrivacidad;
        return privacidadFacade.find(id);
    }
    
}
