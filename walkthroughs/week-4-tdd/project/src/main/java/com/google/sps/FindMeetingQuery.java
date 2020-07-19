// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

// package com.google.sps;

// import java.util.Collection;

// public final class FindMeetingQuery {
//   public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {
//     for (Event e : events) {
//         System.out.print("\nprinting event: \n");
//         System.out.print(e.getTitle());
//     }
//     Collection<TimeRange> test = Collections.emptySet();

//     return test;
//   }
// }

package com.google.sps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public final class FindMeetingQuery {
  public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {
    // Check whether meeting duration is over 1 day
    if (IsRequestOverOneDay(request)) {
      return Collections.emptyList();
    }

    // Start by being available all day
    Collection<TimeRange> availableRange = new ArrayList<TimeRange>();
    availableRange.add(TimeRange.WHOLE_DAY);

    // For each event that has the same attendees than the requested meeting,
    // remove the event time from the availability range
    for (Event currentEvent : events) {
      Collection<String> eventAttendees = currentEvent.getAttendees();
      if (hasCommonAttendees(request.getAttendees(), eventAttendees)) {
        TimeRange eventTime = currentEvent.getWhen();
        updateAvailableRange(availableRange, eventTime, request.getDuration());                
      }
    }

    // Return the availability range
    return availableRange;
  }

  private boolean IsRequestOverOneDay(MeetingRequest request) {
    return request.getDuration() > TimeRange.WHOLE_DAY.duration();
  }

  private boolean hasCommonAttendees(Collection<String> meetingAttendees, Collection<String> eventAttendees) {
    return !Collections.disjoint(meetingAttendees, eventAttendees);
  }

  private void updateAvailableRange(Collection<TimeRange> availableRange, TimeRange eventTime, long meetingDuration) {
    // For each potential range that overlaps the event time
    for (TimeRange potentialRange: new ArrayList<TimeRange>(availableRange)) {
      if (potentialRange.overlaps(eventTime)) {
        // Create new availability time slots around the event time in the potential range
        Collection<TimeRange> newTimeslots = splitTimeslot(potentialRange, eventTime);

        // Remove the potential range from the availability range
        availableRange.remove(potentialRange);

        // Check if the new availability time slots are long enough to accomodate the event
        // If so, re-add them to the availability range
        for (TimeRange newTimeslot : newTimeslots) {
          if (isTimeslotLongEnough(newTimeslot, meetingDuration)) {
            availableRange.add(newTimeslot);
          }
        }
      }
    }
  }

  private Collection<TimeRange> splitTimeslot(TimeRange availableTimeslot, TimeRange eventTime) {
    // Remove eventTime from availableTimeslot
    // availableTimeslot:           |----------|
    // eventTime:                       |---|
    // output (new avaiabilities):  |---|   |--|
    return Arrays.asList(
      TimeRange.fromStartEnd(availableTimeslot.start(), eventTime.start(), false),
      TimeRange.fromStartEnd(eventTime.end(), availableTimeslot.end(), false)
    );
  }

  private boolean isTimeslotLongEnough(TimeRange timeslot, long meetingDuration) {
    return timeslot.duration() >= meetingDuration;
  }
}
