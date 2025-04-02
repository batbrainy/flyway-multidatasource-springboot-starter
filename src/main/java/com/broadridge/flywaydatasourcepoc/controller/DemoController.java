package com.broadridge.flywaydatasourcepoc.controller;


import com.broadridge.flywaydatasourcepoc.model.one.TableOne;
import com.broadridge.flywaydatasourcepoc.model.two.TableTwo;
import com.broadridge.flywaydatasourcepoc.repository.one.TableOneRepository;
import com.broadridge.flywaydatasourcepoc.repository.two.TableTwoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

  @Autowired
  private TableOneRepository tableOneRepository;

  @Autowired
  private TableTwoRepository tableTwoRepository;

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
}
