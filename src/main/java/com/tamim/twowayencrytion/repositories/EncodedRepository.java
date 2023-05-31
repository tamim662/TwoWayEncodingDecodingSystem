package com.tamim.twowayencrytion.repositories;

import com.tamim.twowayencrytion.entities.Encoded;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncodedRepository extends JpaRepository<Encoded, Integer> {
}
