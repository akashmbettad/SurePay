package com.java.surepay.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.java.surepay.Entity.Record;

@Repository
public interface RecordRepository extends CrudRepository<Record, Long> {

	@Query("SELECT r from Record r WHERE r.reference=:reference")
	public Optional<Record> findByreference(@Param("reference")Long reference);

}
