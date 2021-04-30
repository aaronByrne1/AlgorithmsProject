import java.time.*;
public class stopTime {
	
	private int trip_id;
	private LocalTime arrival_time;
	private LocalTime departure_time;
	private int stop_id;
	private int stop_sequence;
	private int stop_headsign;
	private int pickup_type;
	private int drop_off_type;
	private float shape_dist_traveled;

	stopTime(int trip_id, LocalTime arrival_time, LocalTime departure_time, int stop_id, int stop_sequence, String stop_headsign,
			 int pickup_type, int drop_off_type, float shape_dist_traveled) {
			
		this.trip_id = trip_id;
		this.arrival_time = arrival_time;
		this.departure_time = departure_time;
		this.stop_id = stop_id;
		this.stop_sequence = stop_sequence;
		try {
			this.stop_headsign = Integer.parseInt(stop_headsign);
		} catch(NumberFormatException e) {
			this.stop_headsign = 0;
		}
		this.pickup_type = pickup_type;
		this.drop_off_type = drop_off_type;
		this.shape_dist_traveled = shape_dist_traveled;		
	}
	
	public void print()
	{
		System.out.print("Trip_id = " + trip_id + "\n" + "Arrival Time = " + arrival_time + "\n" + "Departure Time = " + departure_time + "\n" + "Stop ID =  " +
				stop_id + "\n" + "Stop Sequence = " + stop_sequence + "Pickup Type = " + pickup_type + "\n" + "Stop Headsign = "
				+ stop_headsign + "\n"+ "Drop Off Type = " + drop_off_type +"\n" + "Distance Travelled =  " + shape_dist_traveled + "\n" + "\n");
	}
	public LocalTime getArrivalTime()
	{
		return arrival_time;
	}
	
	public int getTripId()
	{
		return trip_id;
	}
	
	public void setTripId(int i)
	{
		trip_id = i;
	}
} 
