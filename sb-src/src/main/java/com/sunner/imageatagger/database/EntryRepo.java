package com.sunner.imageatagger.database;

import com.sunner.imageatagger.database.model.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepo extends JpaRepository<Entry, Long> {
}
