package org.example.MovieTicketing1.strategy.locking;

import org.example.CustomerIssueResolution1.model.Seat;
import org.example.CustomerIssueResolution1.model.SeatLock;
import org.example.CustomerIssueResolution1.model.Show;
import org.example.CustomerIssueResolution1.model.User;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemorySeatLockProvider implements ISeatLockProvider {

    private final Map<Show, Map<Seat, SeatLock>> locks;
    private final Integer lockTimeOut;

    public InMemorySeatLockProvider(Integer lockTimeOut) {
        this.lockTimeOut = lockTimeOut;
        locks = new ConcurrentHashMap<>();
    }

    @Override
    public void lockSeats(Show show, List<Seat> seats, User user) throws Exception {
        Map<Seat, SeatLock> seatLockMap = locks.computeIfAbsent(show, s -> new ConcurrentHashMap<>());

        synchronized (seatLockMap) {
            for (Seat seat : seats) {
                if (seatLockMap.containsKey(seat) && !seatLockMap.get(seat).isLockExpired()) {
                    throw new Exception("Seat " + seat.getId() + " already locked");
                }
            }

            Date now = new Date();
            for (Seat seat : seats) {
                seatLockMap.put(seat, new SeatLock(seat, show, lockTimeOut, now, user));
            }
        }
    }

    @Override
    public void unlockSeats(Show show, List<Seat> seats, User user) {
        Map<Seat, SeatLock> seatLockMap = locks.get(show);
        if (seatLockMap == null) return;

        synchronized (seatLockMap) {
            for (Seat seat : seats) {
                SeatLock lock = seatLockMap.get(seat);
                if (lock != null && lock.getLockedBy().equals(user)) {
                    seatLockMap.remove(seat);
                }
            }
        }
    }

    @Override
    public boolean validateLock(Show show, Seat seat, User user) {
        Map<Seat, SeatLock> seatLockMap = locks.get(show);
        if (seatLockMap == null) return false;
        synchronized (seatLockMap) {
            SeatLock lock = seatLockMap.get(seat);
            return lock != null && !lock.isLockExpired() && lock.getLockedBy().equals(user);
        }
    }

    @Override
    public List<Seat> getLockedSeats(Show show) {
        Map<Seat, SeatLock> seatLockMap = locks.get(show);
        if (seatLockMap == null) {
            return Collections.emptyList();
        }
        synchronized (seatLockMap) {
            return seatLockMap.keySet().stream().filter(key -> !seatLockMap.get(key).isLockExpired()).collect(Collectors.toList());
        }
    }
}
