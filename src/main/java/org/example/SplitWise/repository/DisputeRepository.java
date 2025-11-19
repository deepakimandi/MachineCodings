package org.example.SplitWise.repository;

import org.example.SplitWise.model.Dispute;
import org.example.SplitWise.model.Group;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DisputeRepository {
    private final Map<String, Dispute> disputes = new HashMap<>();

    public Optional<Dispute> findById(String id) {
        return Optional.ofNullable(disputes.get(id));
    }

    public void save(Dispute dispute) {
        disputes.put(dispute.getId(), dispute);
    }
}
