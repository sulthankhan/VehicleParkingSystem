import java.util.*;
import java.sql.Date;

class ParkingSystemApp {
    static ParkingSystemFrame mainFrame;
    private ArrayList<ParkingSlot> slots = null;
    ArrayList<Ticket> ticketList = null;
    ParkingSlot slot = null;
    private long startTimeMilliseconds;
    private long startTime = 0;
    private long endTimeMilliseconds;
    private String durationParked;
    private Date date;
    private static final double fee = 0.5;
    private static final int minimumTime = 15;
    int timeInMinutes = 0;
    private double totalFee = 0;
    Paymentinformation payInfo = null;
    public ParkingSystemApp()
    {
        ParkingLot lot = new ParkingLot();
        slots = lot.getParkingSlots();
        ticketList = new ArrayList<>();
    }
    public Ticket park()
    {
        ParkingSlot slot = checkAvailability();
        if (slot != null)
        {
            startTimeMilliseconds = System.currentTimeMillis();
            Ticket ticket = new Ticket(slot.getSlotNumber(), startTimeMilliseconds, date);
            ticketList.add(ticket);
            slot.setAvailability(false);
            return ticket;
        }
        return null;
    }
    public ParkingSlot checkAvailability()
    {
        for(int i = 0; i < slots.size(); i++)
        {
            slot = slots.get(i);
            if(slot.getAvailability() == true)
            {
                return slot;
            }
        }
        return null;
    }
    public void captureEndTime()
    {
        endTimeMilliseconds = System.currentTimeMillis();
    }
    public boolean validateTicketNumber(int ticketNumEntered)
    {
        boolean isValid = false;
        for (int i = 0; i < ticketList.size(); i++)
        {
            int slotNumber = ticketList.get(i).getSlotNumber();
            if (ticketNumEntered == slotNumber)
            {
                isValid = true;
                startTime = ticketList.get(i).getStartTime();
                break;
            }
        }
        return isValid;
    }
    public void calculateTotalMinutes()
    {
        long durationMilliSeconds = endTimeMilliseconds - startTime;
        durationParked = convertTimeFormat(durationMilliSeconds);
        String [] time = durationParked.split(":");
        int hours = Integer.parseInt(time[0]);
        int minutes = Integer.parseInt(time[1]);
        int seconds = Integer.parseInt(time[2]);
        timeInMinutes = (hours * 60) + minutes + (seconds / 60);
    }
    public double getTotalFee()
    {
        if(totalFee == 0)
        {
            if (timeInMinutes < 15)
                totalFee = 0.5;
            else
                totalFee = (timeInMinutes / minimumTime) * fee;

        }
        return totalFee;
    }
    public void makeSlotAvailable(int ticketNumber){
        for (int i = 0; i < slots.size(); i++)
        {
            int slotNumber = slots.get(i).getSlotNumber();
            if (ticketNumber == slotNumber)
            {
                slot = slots.get(i);
                slot.setAvailability(true);
            }
        }
    }
    public void setPaymentInformation(String ccNumber, String cvvNumber, String expiry)
    {
        payInfo = new Paymentinformation(ccNumber, cvvNumber, expiry);
    }
    public boolean validateCreditCard()
    {
        Bank bank = new Bank(payInfo);
        return bank.validateCreditCard();
    }
    public String convertTimeFormat(long milliSeconds)
    {
        long totalSeconds = milliSeconds / 1000;
        long currentSecond = totalSeconds % 60;
        long totalMinutes = totalSeconds / 60;
        long currentMinute = totalMinutes % 60;
        long totalHours = totalMinutes / 60;
        long currentHour = totalHours % 24;
        return currentHour + ":" + currentMinute + ":" + currentSecond;
    }
}
