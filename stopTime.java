import java.time.*;
public class stopTime {
	
	private int trip_id;
	private LocalTime arrival_time;
	private LocalTime departure_time;
	private int stop_id;
	private int stop_sequence;
	private int pickup_type;
	private int drop_off_type;
	private float shape_dist_traveled;
	
	stopTime(int trip_id, LocalTime arrival_time, LocalTime departure_time, int stop_id, int stop_sequence,
			 int pickup_type, int drop_off_type, float shape_dist_traveled) {
			
		this.trip_id = trip_id;
		this.arrival_time = arrival_time;
		this.departure_time = departure_time;
		this.stop_id = stop_id;
		this.stop_sequence = stop_sequence;
		//this.stop_headsign = stop_headsign;
		this.pickup_type = pickup_type;
		this.drop_off_type = drop_off_type;
		this.shape_dist_traveled = shape_dist_traveled;		
	}
	
	public void print()
	{
		System.out.print(trip_id + " " + arrival_time + " " + " " + departure_time + " " + stop_id + " " + stop_sequence + " " + pickup_type + " "
				+ drop_off_type + " " + shape_dist_traveled);
	}
	
	public LocalTime getArrivalTime()
	{
		return arrival_time;
	}
} 
