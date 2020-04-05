package com.kovunov.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Arrays;

@FacesValidator
public class PlayerValidator implements Validator {
    private String[] playerNames = {"Ronaldo", "Romario", "Shevchenko"};


    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object name) {
        if (!Arrays.asList(playerNames).contains(name)) {
            FacesMessage msg =
                    new FacesMessage("Player name should be one of these: " + Arrays.toString(playerNames));
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
