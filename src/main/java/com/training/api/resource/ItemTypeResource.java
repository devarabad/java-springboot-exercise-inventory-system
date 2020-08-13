package com.training.api.resource;

import com.training.api.resource.entity.ItemTypeInfo;
import com.training.api.resource.entity.ResourceMessage;
import com.training.api.service.ItemTypeService;
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
@RequestMapping(path = "/items/types")
public class ItemTypeResource
{
  private final ItemTypeService itemTypeService;

  public ItemTypeResource(ItemTypeService itemTypeService)
  {
    this.itemTypeService = itemTypeService;
  }

  @GetMapping
  public ResponseEntity<List<ItemTypeInfo>> getAllItemType()
  {
    return new ResponseEntity<>(itemTypeService.getAllItemType(), HttpStatus.OK);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<ItemTypeInfo> getItemType(@PathVariable("id") long id)
  {
    try
    {
      ItemTypeInfo itemTypeInfo = itemTypeService.getItemType(id);
      return new ResponseEntity<>(itemTypeInfo, HttpStatus.OK);
    }
    catch (IllegalArgumentException e)
    {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND
        , "Item type not found.");
    }
  }

  @PostMapping
  public ResponseEntity<ItemTypeInfo> addItemType(@RequestBody ItemTypeInfo itemTypeInfo)
  {
    try
    {
      ItemTypeInfo addedItemTypeInfo = itemTypeService.addItemType(itemTypeInfo);
      return new ResponseEntity<>(addedItemTypeInfo, HttpStatus.CREATED);
    }
    catch (IllegalArgumentException e)
    {
      throw new ResponseStatusException(
        HttpStatus.BAD_REQUEST
        , e.getMessage());
    }
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<ItemTypeInfo> updateItemType(@PathVariable("id") long id, @RequestBody ItemTypeInfo itemTypeInfo)
  {
    try
    {
      ItemTypeInfo updatedItemTypeInfo = itemTypeService.updateItemType(id, itemTypeInfo);
      return new ResponseEntity<>(updatedItemTypeInfo, HttpStatus.OK);
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
  public ResponseEntity<String> deleteItemType(@PathVariable("id") long id)
  {
    if (itemTypeService.deleteItemType(id))
    {
      return new ResponseEntity<>("1", HttpStatus.OK);
    }
    else
    {
      return new ResponseEntity<>("0", HttpStatus.OK);
    }
  }

  @PostMapping(path = "/{id}/activate")
  public ResponseEntity<ResourceMessage> activateItemType(@PathVariable("id") long id)
  {
    try
    {
      itemTypeService.activateItemType(id);
      ResourceMessage resourceMessage =
        new ResourceMessage.ResourceMessageBuilder( "success"
                                                    , "Item type has been activated.").build();

      return new ResponseEntity<>(resourceMessage, HttpStatus.OK);
    }
    catch (IllegalArgumentException e)
    {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND
        , "Nothing to activate. Item type not found.");
    }
  }

  @PostMapping(path = "/{id}/deactivate")
  public ResponseEntity<ResourceMessage> deactivateItemType(@PathVariable("id") long id)
  {
    try
    {
      itemTypeService.deactivateItemType(id);
      ResourceMessage resourceMessage =
        new ResourceMessage.ResourceMessageBuilder( "success"
                                                    , "Item type has been deactivated.").build();

      return new ResponseEntity<>(resourceMessage, HttpStatus.OK);
    }
    catch (IllegalArgumentException e)
    {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND
        , "Nothing to deactivate. Item type not found.");
    }
  }
}
