package com.epam.esm.response;

import com.epam.esm.attribute.ResponseAttribute;
import com.epam.esm.dao.constant.Symbol;
import com.epam.esm.util.MessageLocale;
import org.springframework.hateoas.RepresentationModel;

import java.util.ResourceBundle;

/**
 * The type Operation response.
 */
public class OperationResponse extends RepresentationModel<OperationResponse> {
    private String operation;
    private String message;

    /**
     * The enum Operation.
     */
    public enum Operation {
        /**
         * Creation operation.
         */
        CREATION(ResponseAttribute.CREATION_OPERATION),
        /**
         * Deletion operation.
         */
        DELETION(ResponseAttribute.DELETION_OPERATION),
        /**
         * Update operation.
         */
        UPDATE(ResponseAttribute.UPDATE_OPERATION),
        /**
         * Other operation.
         */
        OTHER(ResponseAttribute.OTHER_OPERATION);

        private final String nameKey;

        Operation(String nameKey) {
            this.nameKey = nameKey;
        }

        /**
         * Gets localized operation name.
         *
         * @return the localized operation name
         */
        public String getLocalizedOperationName() {
            return ResourceBundle.getBundle(ResponseAttribute.PROPERTY_FILE_NAME,
                    MessageLocale.getCurrent()).getString(nameKey);
        }
    }

    /**
     * Instantiates a new Operation response.
     */
    public OperationResponse() {
    }

    /**
     * Instantiates a new Operation response.
     *
     * @param operation  the operation
     * @param messageKey the message key
     * @param detail     the detail
     */
    public OperationResponse(Operation operation, String messageKey, String detail) {
        this.operation = operation.getLocalizedOperationName();
        this.message = ResourceBundle.getBundle(ResponseAttribute.PROPERTY_FILE_NAME,
                MessageLocale.getCurrent()).getString(messageKey) + Symbol.SPACE + detail;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets operation.
     *
     * @return the operation
     */
    public String getOperation() {
        return operation;
    }

    /**
     * Sets operation.
     *
     * @param operation the operation
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }
}
