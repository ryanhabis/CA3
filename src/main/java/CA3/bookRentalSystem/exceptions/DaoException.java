package CA3.bookRentalSystem.exceptions;

public class DaoException extends Throwable{

    public DaoException() {
    }

    /**
     * Method to create a DaoException when called. It will display the message passed to it.
     * @param aMessage - the message displayed to indicate a Dao Exception occurred.
     */
    public DaoException(String aMessage) {
        super(aMessage);
    }
}
