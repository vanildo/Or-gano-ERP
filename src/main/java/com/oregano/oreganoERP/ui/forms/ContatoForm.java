package com.oregano.oreganoERP.ui.forms;

import java.util.Arrays;

import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.jboss.seam.solder.log.Category;

import com.oregano.oreganoERP.model.Contato;
import com.oregano.oreganoERP.model.Endereco;
import com.oregano.oreganoERP.ui.OreganoErpApplication;
import com.vaadin.data.Item;
import com.vaadin.data.Validator;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class ContatoForm extends VerticalLayout {

	Contato contato;
	OreganoErpApplication application;


    private static final String COMMON_FIELD_WIDTH = "12em";
    @Inject
	@Category("oreganoERP :: ContatoForm")
	private Logger log;

    @SuppressWarnings("unchecked")
	public ContatoForm(OreganoErpApplication erpApplication) {

    	application = erpApplication;

        contato = new Contato(); // a person POJO
        BeanItem contatoItem = new BeanItem(contato); // item from POJO

        // Create the Form
        final Form contatoForm = new Form();
        contatoForm.setCaption("Detalhes do Contato");
        contatoForm.setWriteThrough(false); // we want explicit 'apply'
        contatoForm.setInvalidCommitted(false); // no invalid values in datamodel

        // FieldFactory for customizing the fields and adding validators
        contatoForm.setFormFieldFactory(new ContatoFieldFactory());
        contatoForm.setItemDataSource(contatoItem); // bind to POJO via BeanItem

        // Determines which properties are shown, and in which order:
        contatoForm.setVisibleItemProperties(Arrays.asList(new String[] {
                "nome", "email", "telefone"}));

        // Add form to layout
        addComponent(contatoForm);

        // The cancel / apply buttons
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSpacing(true);
        Button discardChanges = new Button("Desprezar alterações",
                new ClickListener() {
                    public void buttonClick(ClickEvent event) {
                        contatoForm.discard();
                    }
                });

        buttons.addComponent(discardChanges);

        Button apply = new Button("Salvar", new ClickListener() {
            public void buttonClick(ClickEvent event) {
                try {
                    contatoForm.commit();
                    log.info("Salvando o contato " + contato.getNome());

            		//IRepository<Contato> repositorio =  (IRepository<Contato>) ServiceLocator.locate("erp-ear/ContatoRepository/local");

            		contato.setEndereco(new Endereco());
            		contato.getEndereco().setNumero(12);
                    //contato = repositorio.save(contato);
                    //application.getContatos().addItem(contato);

                } catch (Exception e) {
                    // Ignored, we'll let the Form handle the errors
                }
            }
        });
        buttons.addComponent(apply);
        contatoForm.getFooter().addComponent(buttons);
        contatoForm.getFooter().setMargin(false, false, true, true);

        // button for showing the internal state of the POJO
        Button showPojoState = new Button("Exibir o estado interno do POJO",
                new ClickListener() {
                    public void buttonClick(ClickEvent event) {
                        showPojoState();
                    }
                });
        addComponent(showPojoState);
    }

    private void showPojoState() {
        Window.Notification n = new Window.Notification("Estado do POJO",
                Window.Notification.TYPE_TRAY_NOTIFICATION);
        n.setPosition(Window.Notification.POSITION_CENTERED);
        n.setDescription("Nome: " + contato.getNome()
                + "Email: " + contato.getEmail());
        getWindow().showNotification(n);
    }

    private class ContatoFieldFactory extends DefaultFieldFactory {


        public ContatoFieldFactory() {

        }

        @Override
        public Field createField(Item item, Object propertyId,
                Component uiContext) {

            Field f = super.createField(item, propertyId, uiContext);
            if ("nome".equals(propertyId)) {
                TextField tf = (TextField) f;
                tf.setRequired(true);
                tf.setRequiredError("Por Favor entre com seu nome");
                tf.setWidth(COMMON_FIELD_WIDTH);
                tf.addValidator(new StringLengthValidator(
                        "O nome tem que ter entre 3 e 25 caracteres", 3, 25, false));
            } else if ("email".equals(propertyId)) {
                TextField tf = (TextField) f;
                tf.setRequired(true);
                tf.setRequiredError("Por Favor entre com seu email");
                tf.setWidth(COMMON_FIELD_WIDTH);
                tf.addValidator(new EmailValidator("Esse email � inv�lido"));
            } else if ("telefone".equals(propertyId)) {
                TextField tf = (TextField) f;
                //tf.setSecret(true);
                tf.setRequired(true);
                tf.setRequiredError("Please enter a password");
                tf.setWidth("10em");
                tf.addValidator(new StringLengthValidator(
                        "Password must be 6-20 characters", 6, 20, false));
            } else if ("celular".equals(propertyId)) {
                TextField tf = (TextField) f;
                tf.setWidth("2em");
            }

            return f;
        }
    }


    public class IntegerValidator implements Validator {

        private String message;

        public IntegerValidator(String message) {
            this.message = message;
        }

        public boolean isValid(Object value) {
            if (value == null || !(value instanceof String)) {
                return false;
            }
            try {
                Integer.parseInt((String) value);
            } catch (Exception e) {
                return false;
            }
            return true;
        }

        public void validate(Object value) throws InvalidValueException {
            if (!isValid(value)) {
                throw new InvalidValueException(message);
            }
        }

    }
}
