package com.broadridge.flywaydatasourcepoc.repository.two;

import com.broadridge.flywaydatasourcepoc.model.one.TableOne;
import com.broadridge.flywaydatasourcepoc.model.two.TableTwo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableTwoRepository extends JpaRepository<TableTwo, Integer> {
  Optional<TableTwo> findTopByOrderByIdDesc();
}
