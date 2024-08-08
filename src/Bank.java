public class Bank
{
    String creditCardNumber;
    String cvvNumber;
    String expiryDate;
    public Bank(Paymentinformation paymentinformation)
    {
        this.creditCardNumber = paymentinformation.getCreditCardNumber();
        this.cvvNumber = paymentinformation.getCvvNumber();
        this.expiryDate = paymentinformation.getExpiryDate();
    }
    public boolean validateCreditCard()
    {
        return true;
    }
}

