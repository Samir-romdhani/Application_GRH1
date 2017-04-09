package tn.ensi.rh.beans;

import tn.ensi.rh.entities.Evaluation;
import tn.ensi.rh.beans.util.JsfUtil;
import tn.ensi.rh.beans.util.JsfUtil.PersistAction;
import tn.ensi.rh.dao.EvaluationFacade;

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

@Named("evaluationController")
@SessionScoped
public class EvaluationController implements Serializable {

    @EJB
    private tn.ensi.rh.dao.EvaluationFacade ejbFacade;
    private List<Evaluation> items = null;
    private Evaluation selected;

    public EvaluationController() {
    }

    public Evaluation getSelected() {
        return selected;
    }

    public void setSelected(Evaluation selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getEvaluationPK().setIdC(selected.getCompetence().getIdC());
        selected.getEvaluationPK().setIdF(selected.getFormation().getIdF());
        selected.getEvaluationPK().setUserId(selected.getEmploye().getUserId());
    }

    protected void initializeEmbeddableKey() {
        selected.setEvaluationPK(new tn.ensi.rh.entities.EvaluationPK());
    }

    private EvaluationFacade getFacade() {
        return ejbFacade;
    }

    public Evaluation prepareCreate() {
        selected = new Evaluation();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("EvaluationCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("EvaluationUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("EvaluationDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Evaluation> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Evaluation getEvaluation(tn.ensi.rh.entities.EvaluationPK id) {
        return getFacade().find(id);
    }

    public List<Evaluation> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Evaluation> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Evaluation.class)
    public static class EvaluationControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EvaluationController controller = (EvaluationController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "evaluationController");
            return controller.getEvaluation(getKey(value));
        }

        tn.ensi.rh.entities.EvaluationPK getKey(String value) {
            tn.ensi.rh.entities.EvaluationPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new tn.ensi.rh.entities.EvaluationPK();
            key.setIdF(Integer.parseInt(values[0]));
            key.setIdC(Integer.parseInt(values[1]));
            key.setUserId(values[2]);
            return key;
        }

        String getStringKey(tn.ensi.rh.entities.EvaluationPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdF());
            sb.append(SEPARATOR);
            sb.append(value.getIdC());
            sb.append(SEPARATOR);
            sb.append(value.getUserId());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Evaluation) {
                Evaluation o = (Evaluation) object;
                return getStringKey(o.getEvaluationPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Evaluation.class.getName()});
                return null;
            }
        }

    }

}
