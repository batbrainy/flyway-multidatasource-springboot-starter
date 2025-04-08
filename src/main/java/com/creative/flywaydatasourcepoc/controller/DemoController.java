package com.creative.flywaydatasourcepoc.controller;


import com.creative.flywaydatasourcepoc.model.one.TableOne;
import com.creative.flywaydatasourcepoc.model.two.TableTwo;
import com.creative.flywaydatasourcepoc.repository.one.TableOneRepository;
import com.creative.flywaydatasourcepoc.repository.two.TableTwoRepository;
import java.util.Arrays;
import java.util.List;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

  private final DataSource dataSource;
  @Autowired
  private TableOneRepository tableOneRepository;
  @Autowired
  private TableTwoRepository tableTwoRepository;

  @Autowired
  public DemoController(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @GetMapping("/data")
  public String getData() {
    long count1 = tableOneRepository.count();
    long count2 = tableTwoRepository.count();
    return "Table One Count: " + count1 + ", Table Two Count: " + count2;
  }

  @GetMapping("/insertOne")
  public Integer insertIntoOne() {
    TableOne tableOne = new TableOne();
    tableOne.setId(tableOneRepository.findTopByOrderByIdDesc().map(x->x.getId() +1).orElse(1));
    tableOne.setName("foo");
    tableOneRepository.saveAndFlush(tableOne);
    return tableOne.getId();
  }

  @GetMapping("/insertTwo")
  public Integer insertIntoTwo() {
    TableTwo tableTwo = new TableTwo();
    tableTwo.setId(tableTwoRepository.findTopByOrderByIdDesc().map(x->x.getId() +1).orElse(1));
    tableTwo.setDescription("bar");
    tableTwoRepository.saveAndFlush(tableTwo);
    return tableTwo.getId();
  }

  @GetMapping("/dataMigration")
  public String getRunMultipleSchemaMigration() {
    List<String> schemasToMigrate = Arrays.asList("inventory", "reporting", "audit");
    String migrationLocation = "classpath:db/migration/db1";

    for (String schema : schemasToMigrate) {
      Flyway flyway = Flyway.configure()
          .dataSource(dataSource)
          .schemas(schema) // Migrate one schema at a time
          .locations(migrationLocation)
          .createSchemas(true)
          .load();

      System.out.println("Applying migrations to schema: " + schema);
      flyway.migrate();
    }
    return "done";
  }
}
