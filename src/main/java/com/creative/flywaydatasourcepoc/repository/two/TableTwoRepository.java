package com.creative.flywaydatasourcepoc.repository.two;

import com.creative.flywaydatasourcepoc.model.two.TableTwo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableTwoRepository extends JpaRepository<TableTwo, Integer> {
  Optional<TableTwo> findTopByOrderByIdDesc();
}
