package com.training.api.service;

import com.training.api.resource.entity.ItemTypeInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ItemTypeServiceTest
{
  @Autowired
  private ItemTypeService itemTypeService;

  @Test
  public void getAllItemType()
  {
    List<ItemTypeInfo> itemTypeInfoList = itemTypeService.getAllItemType();
    itemTypeInfoList.forEach(System.out::println);
  }
}
