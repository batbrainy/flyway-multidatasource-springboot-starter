package com.broadridge.flywaydatasourcepoc.repository.one;

import com.broadridge.flywaydatasourcepoc.model.one.TableOne;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableOneRepository extends JpaRepository<TableOne, Integer> {
  Optional<TableOne> findTopByOrderByIdDesc();
}
