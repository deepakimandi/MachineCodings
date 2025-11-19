package org.example.SplitWise.repository;

import org.example.SplitWise.model.Group;

import java.util.Optional;

public interface GroupRepository {
    Optional<Group> findById(String id);
    void save(Group group);
}
