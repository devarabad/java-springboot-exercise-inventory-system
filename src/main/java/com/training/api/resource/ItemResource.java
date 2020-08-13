package com.training.api.resource;

import com.training.api.resource.entity.ItemInfo;
import com.training.api.resource.entity.ResourceMessage;
import com.training.api.service.ItemService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @since   2020-08-12
 * @author  Andrew Ray Abad
 */
@RestController
@RequestMapping(path = "/items")
public class ItemResource
{
  private final ItemService itemService;

  public ItemResource(ItemService itemService)
  {
    this.itemService = itemService;
  }

  @GetMapping
  public ResponseEntity<List<ItemInfo>> getAllItem()
  {
    return new ResponseEntity<List<ItemInfo>>(itemService.getAllItem(), HttpStatus.OK);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<ItemInfo> getItem(@PathVariable("id") long id)
  {
    try
    {
      ItemInfo itemInfo = itemService.getItem(id);
      return new ResponseEntity<>(itemInfo, HttpStatus.OK);
    }
    catch (IllegalArgumentException e)
    {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND
        , e.getMessage());
    }
  }

  @PostMapping
  public ResponseEntity<ItemInfo> addItem(@RequestBody ItemInfo itemInfo)
  {
    try
    {
      ItemInfo addedItemInfo = itemService.addItem(itemInfo);
      return new ResponseEntity<>(addedItemInfo, HttpStatus.CREATED);
    }
    catch (IllegalArgumentException e)
    {
      throw new ResponseStatusException(
        HttpStatus.BAD_REQUEST
        , e.getMessage());
    }
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<ItemInfo> updateItem(@PathVariable("id") long id, @RequestBody ItemInfo itemInfo)
  {
    try
    {
      ItemInfo updatedItemInfo = itemService.updateItem(id, itemInfo);
      return new ResponseEntity<>(updatedItemInfo, HttpStatus.OK);
    }
    catch (IllegalArgumentException e)
    {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND
        , e.getMessage());
    }
  }

  /**
   * Delete an item.
   * Note:
   *  - As per requirements
   *    - Response is either 1 or 0 (zero means fail one means successful)
   * @param id
   * @return
   */
  @DeleteMapping(path = "/{id}", produces = {MediaType.TEXT_PLAIN_VALUE})
  public ResponseEntity<String> deleteItem(@PathVariable("id") long id)
  {
    if (itemService.deleteItem(id))
    {
      return new ResponseEntity<>("1", HttpStatus.OK);
    }
    else
    {
      return new ResponseEntity<>("0", HttpStatus.OK);
    }
  }

  @PostMapping(path = "/{id}/activate")
  public ResponseEntity<ResourceMessage> activateItem(@PathVariable("id") long id)
  {
    try
    {
      itemService.activateItem(id);
      ResourceMessage resourceMessage =
        new ResourceMessage.ResourceMessageBuilder( "success"
                                                    , "Item has been activated.").build();

      return new ResponseEntity<>(resourceMessage, HttpStatus.OK);
    }
    catch (IllegalArgumentException e)
    {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND
        , e.getMessage());
    }
  }

  @PostMapping(path = "/{id}/deactivate")
  public ResponseEntity<ResourceMessage> deactivateItem(@PathVariable("id") long id)
  {
    try
    {
      itemService.deactivateItem(id);
      ResourceMessage resourceMessage =
        new ResourceMessage.ResourceMessageBuilder( "success"
                                                    , "Item has been deactivated.").build();

      return new ResponseEntity<>(resourceMessage, HttpStatus.OK);
    }
    catch (IllegalArgumentException e)
    {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND
        , e.getMessage());
    }
  }

  @GetMapping(value = "/count", produces = {MediaType.TEXT_PLAIN_VALUE})
  public ResponseEntity<String> getTotalCountOfItem(@Param("typeId") Long typeId)
  {
    try
    {
      Long totalItemCount = itemService.getTotalCountOfItems(typeId);
      return new ResponseEntity<String>(totalItemCount.toString(), HttpStatus.OK);
    }
    catch (IllegalArgumentException e)
    {
      throw new ResponseStatusException(
        HttpStatus.BAD_REQUEST
        , e.getMessage());
    }
  }

  @GetMapping(value = "/cost", produces = {MediaType.TEXT_PLAIN_VALUE})
  public ResponseEntity<String> getTotalCostOfItem(@Param("typeId") Long typeId)
  {
    try
    {
      Double totalItemCost = itemService.getTotalCostOfItems(typeId);
      return new ResponseEntity<String>(totalItemCost.toString(), HttpStatus.OK);
    }
    catch (IllegalArgumentException e)
    {
      throw new ResponseStatusException(
        HttpStatus.BAD_REQUEST
        , e.getMessage());
    }
  }
}
