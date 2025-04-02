package com.creative.flywaydatasourcepoc.repository.one;

import com.creative.flywaydatasourcepoc.model.one.TableOne;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableOneRepository extends JpaRepository<TableOne, Integer> {
  Optional<TableOne> findTopByOrderByIdDesc();
}
