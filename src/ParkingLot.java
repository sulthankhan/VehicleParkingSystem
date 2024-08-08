import java.util.*;

class ParkingLot
{
    private static final int numberOfSlots = 10;
    private ArrayList<ParkingSlot> listOfSlots = null;
    public ParkingLot()
    {
        listOfSlots = new ArrayList<>();
    }
    public ArrayList<ParkingSlot> getParkingSlots()
    {
        for (int i = 1; i <= numberOfSlots; i++)
        {
            ParkingSlot slot = new ParkingSlot(i, true);
            listOfSlots.add(slot);
        }
        return listOfSlots;
    }
}