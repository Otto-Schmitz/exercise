/**
 * MeetingScheduler
 *
 * A meeting room scheduling system that prevents time conflicts.
 *
 * Each room is identified by a roomId and can have multiple bookings during the day,
 * as long as there is no overlap between intervals.
 *
 * Time is represented in minutes since 00:00 within the day range:
 * [0, 1440).
 *
 * Rules:
 *
 * 1) A booking is a half-open interval [start, end):
 *    - start is inclusive
 *    - end is exclusive
 *
 * 2) Two bookings overlap if:
 *    max(start1, start2) < min(end1, end2)
 *
 * 3) A booking is valid only if:
 *    - 0 <= start < end <= 1440
 *
 * Core functionalities:
 *
 * - book(roomId, start, end)
 *   Attempts to book the room for the given interval.
 *   Returns true if the booking is created.
 *   Returns false if there is a conflict or the interval is invalid.
 *
 * - getBookings(roomId)
 *   Returns the list of bookings for the room sorted by start time.
 *
 * - nextAvailable(roomId, duration)
 *   Returns the earliest time t >= 0 such that the interval
 *   [t, t + duration) is available in the room within the same day.
 *   Returns -1 if no suitable slot exists.
 *
 * Problem goal:
 *
 * Evaluate data modeling, use of ordered data structures,
 * efficient interval conflict detection,
 * handling of edge cases, and code clarity.
 */

package exercise;

import java.util.*;

public class MeetingScheduler {
    private TreeMap<String, TreeMap<Integer, Interval>> map = new TreeMap<>();

    public boolean book(String roomId, int start, int end) {
        TreeMap<Integer, Interval> intervals = map.computeIfAbsent(roomId, k -> new TreeMap<>());
        Interval current = new Interval(start, end);

        if (!isValidRange(current) || hasOverlap(intervals, current)) return false;

        intervals.put(start, current);
        return true;
    }

    private boolean hasOverlap(TreeMap<Integer, Interval> intervals, Interval current) {
        Integer start = current.start();

        Map.Entry<Integer, Interval> floor = intervals.floorEntry(start);
        Map.Entry<Integer, Interval> ceiling = intervals.ceilingEntry(start);

        // verifica o anterior
        if (floor != null && floor.getValue().end() > current.start()) {
            return true;
        }

        // verifica o próximo
        if (ceiling != null && current.end() > ceiling.getValue().start()) {
            return true;
        }

        return false;
    }

    private boolean isValidRange(Interval interval) {
        return interval.start >= 0 && interval.end <= 1440;
    }

    public List<Interval> getBookings(String roomId) {
        TreeMap<Integer, Interval> intervals = map.get(roomId);

        return intervals == null
                ? List.of()
                : intervals.values()
                    .stream()
                    .toList();
    }

    public int nextAvailable(String roomId, int duration) {
        List<Interval> intervals = getBookings(roomId);

        if (intervals.isEmpty()) return duration > 1440 ? -1 : 0;

        for (int i = 0; i < intervals.size() - 1; i++) {
            if (i == 0 && intervals.get(i).start - duration >= 0) return 0;

            if (intervals.get(i).end + duration <= intervals.get(i + 1).start) return intervals.get(i).end;
        }

        return intervals.getLast().end() + duration <= 1440 ? intervals.getLast().end : -1;
    }

    public record Interval(int start, int end) {
        @Override
            public String toString() {
                return "Interval{" +
                        "start=" + start +
                        ", end=" + end +
                        '}';
            }
        }

    public static void main(String[] args) {
        MeetingScheduler ms = new MeetingScheduler();

        System.out.println(ms.book("A", 1, 4));
        System.out.println(ms.book("A", 2, 3));
        System.out.println(ms.book("A", 4, 7));
        System.out.println(ms.book("B", 1, 4));
        System.out.println(ms.book("B", 10, 20));
        System.out.println(ms.getBookings("A"));
        System.out.println(ms.getBookings("B"));
        System.out.println(ms.nextAvailable("A", 10));
        System.out.println(ms.nextAvailable("B", 7));

        System.out.println(ms.book("A", -1, 0));
        System.out.println(ms.book("A", 1000, 2000));


    }
}