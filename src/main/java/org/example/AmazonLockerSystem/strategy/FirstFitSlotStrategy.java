//package org.example.AmazonLockerSystem.strategy;
//
//import org.example.AmazonLockerSystem.model.Slot;
//
//import java.util.List;
//
//public class FirstFitSlotStrategy implements SlotAssignmentStrategy {
//
//    @Override
//    public Slot assignSlot(List<Slot> eligibleSlots) {
//        for (Slot slot : eligibleSlots) {
//            if (slot.acquire()) {
//                return slot;
//            }
//        }
//        return null;
//    }
//}
