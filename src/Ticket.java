import java.sql.Date;

class Ticket {
    private int slotNumber;
    private long startTime;
    private Date date;

    public Ticket(int slotNumber, long startTime, Date date) {
        this.slotNumber = slotNumber;
        this.startTime = startTime;
        this.date = date;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public long getStartTime() {
        return startTime;
    }

    public Date getDate() {
        return date;
    }
}