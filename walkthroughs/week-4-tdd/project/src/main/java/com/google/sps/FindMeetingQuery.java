import java.util.Collection;


public final class FindMeetingQuery {	public final class FindMeetingQuery {

  public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {	  
    
    public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {
      List<TimeRange> mainTimes = new ArrayList<>();
      ArrayList<TimeRange> availableTimes = new ArrayList<>();

      for (Event event : events) {
        Set<String> duplicateAttendees = new HashSet<String>(request.getAttendees());
        duplicateAttendees.retainAll(event.getAttendees()); 
        if (duplicateAttendees.size() >= 1) {
          mainTimes.add(event.getWhen()); 
        }
      }

      int prevStart = 0;
      mainTimes.add(TimeRange.fromStartDuration(1440, 0)); 
      Collections.sort(mainTimes, TimeRange.ORDER_BY_START); /

      for (TimeRange currTime : mainTimes) {
        if (currTime.start() > prevStart) {
          if (currTime.start() - prevStart >= request.getDuration()) {
            availableTimes.add(TimeRange.fromStartEnd(prevStart, currTime.start(), false));
          }
          prevStart = currTime.end();
        } else if (currTime.end() > prevStart) { 
          prevStart = currTime.end();
        }
      }
      return availableTimes;
    }	  
  
  }

  }	

}
