/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.MusicsFacade;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import dao.Musics;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author cbkremer
 */
@Named("musicsController")
@SessionScoped
public class MusicsController implements Serializable {
    
    @EJB
    private bean.MusicsFacade ejbFacade;
    private List<Musics> items = null;
    private Musics selected;
    
    public MusicsController(){
    }
    
    public Musics getSelected(){
        return selected;
    }
    public void setSelected(Musics selected){
        this.selected = selected;
    }
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }
    private MusicsFacade getFacade() {
        return ejbFacade;
    }
    public Musics prepareCreate(){
        selected = new Musics();
        initializeEmbeddableKey();
        return selected;
    }
    public void create(){
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UniversityCreated"));
        if(!JsfUtil.isValidationFailed()){
            items = null;// Invalidate list of items to trigger re-query.
        }
    }
    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UniversityUpdated"));
        if (!JsfUtil.isValidationFailed()) {
            //selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UniversityDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    public List<Musics> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    private void persist(PersistAction persistaction, String successMessage){
        if(selected != null){
            setEmbeddableKeys();
            try{
                if(persistaction != PersistAction.DELETE){
                    getFacade().edit(selected);
                }
                else{
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            }
            catch(EJBException ex){
                String msg = "";
                Throwable cause = ex.getCause();
                if(cause != null){
                    msg = cause.getLocalizedMessage();
                }
                if(msg.length() > 0){
                    JsfUtil.addErrorMessage(msg);
                }
                else{
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            }
            catch(Exception ex){
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }
    public Musics getMusics(java.lang.Long id){
        return getFacade().find(id);
    }
    public List<Musics> getItemsAvailableSelectMany(){
        return getFacade().findAll();
    }
    public List<Musics> getItemsAvailableSelectOne(){
        return getFacade().findAll();
    }
    
    @FacesConverter(forClass = Musics.class)
    public static class MusicsControllerConverter implements Converter{
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MusicsController controller = (MusicsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "musicsController");
            return controller.getMusics(getKey(value));
        }
        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }
        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }
        @Override     //fixes musics controller converter error
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Musics) {
                Musics o = (Musics) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Musics.class.getName()});
                return null;
            }
        }
    }
    
}
