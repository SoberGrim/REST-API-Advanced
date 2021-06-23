package com.epam.esm.response;

import com.epam.esm.attribute.OperationResponseAttribute;
import com.epam.esm.dao.constant.Symbol;
import com.epam.esm.util.MessageLocale;
import org.springframework.hateoas.RepresentationModel;

import java.util.ResourceBundle;

public class OperationResponse extends RepresentationModel<OperationResponse> {
    private String operation;
    private String message;

    public enum Operation {
        CREATION(OperationResponseAttribute.CREATION_OPERATION),
        DELETION(OperationResponseAttribute.DELETION_OPERATION),
        OTHER(OperationResponseAttribute.OTHER_OPERATION);

        private final String nameKey;

        Operation(String nameKey) {
            this.nameKey = nameKey;
        }

        public String getLocalizedOperationName() {
            return ResourceBundle.getBundle(OperationResponseAttribute.PROPERTY_FILE_NAME,
                    MessageLocale.getCurrent()).getString(nameKey);
        }
    }

    public OperationResponse() {
    }

    public OperationResponse(Operation operation, String messageKey, String detail) {
        this.operation = operation.getLocalizedOperationName();
        this.message = ResourceBundle.getBundle(OperationResponseAttribute.PROPERTY_FILE_NAME,
                MessageLocale.getCurrent()).getString(messageKey) + Symbol.SPACE_SYMBOL + detail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
