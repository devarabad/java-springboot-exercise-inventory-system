package com.training.api.service;

import com.training.api.data.model.Item;
import com.training.api.data.model.ItemType;
import com.training.api.data.repository.ItemRepository;
import com.training.api.resource.entity.ItemInfo;
import com.training.api.resource.entity.ItemInfoType;
import com.training.api.service.converter.ItemModelConverter;
import com.training.api.service.converter.ModelConverter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * @since   2020-08-12
 * @author  Andrew Ray Abad
 */
@Service
public class ItemService
{
  private final Logger logger = Logger.getLogger(getClass().getName());

  private final ItemRepository itemRepository;

  public ItemService(ItemRepository itemRepository)
  {
    this.itemRepository = itemRepository;
  }

  /**
   * Get all item.
   * @return
   */
  public List<ItemInfo> getAllItem()
  {
    Iterable<Item> itemList                       = this.itemRepository.findAll();
    ModelConverter<ItemInfo, Item> modelConverter = new ItemModelConverter();
    return modelConverter.convertToResourceModelList(itemList);
  }

  /**
   * Get an item.
   * @param id
   * @return
   */
  public ItemInfo getItem(long id)
  {
    Optional<Item> optional = this.itemRepository.findById(id);

    if (optional.isPresent())
    {
      ModelConverter<ItemInfo, Item> modelConverter = new ItemModelConverter();
      return modelConverter.convertToResourceModel(optional.get());
    }
    else
    {
      // Throw exception. Update failed, Item not found
      throw new IllegalArgumentException("Item not found.");
    }
  }

  /**
   * Add an item.
   * Note:
   *  - As per requirements
   *    - itemId is generated
   *    - type is from the type table
   *    - count is not required
   *    - date is system generated(date now)
   *    - count default value is 0
   *    - cost default value is 0
   *    - item is active by default)
   * @param itemInfo
   * @return
   */
  public ItemInfo addItem(ItemInfo itemInfo)
  {
    ItemInfoType itemInfoType = itemInfo.getType();

    if (itemInfoType == null || itemInfoType.getId() == null)
    {
      // Throw exception. Missing required field [type.id]
      // TODO: Move validator to Resource
      throw new IllegalArgumentException("Missing required field [type.id]");
    }

    Long typeId       = itemInfoType.getId();
    ItemType itemType = new ItemType(typeId);

    Item item = new Item();
    item.setName(itemInfo.getName());
    item.setDescription(itemInfo.getDescription());
    item.setCost(itemInfo.getCost() != null ? itemInfo.getCost() : 0.00);
    item.setCount(itemInfo.getCount() != null ? itemInfo.getCount() : 0);
    // item.setActive(itemInfo.getActive() != null ? (itemInfo.getActive() ? 1: 0) : 0);
    item.setActive(1);
    item.setCreatedDate(new Date());
    item.setType(itemType);

    ModelConverter<ItemInfo, Item> modelConverter = new ItemModelConverter();
    return modelConverter.convertToResourceModel(this.itemRepository.save(item));
  }

  /**
   * Update an item.
   * Note:
   *  - As per requirements
   *    - Allowed to be edited are: name, type of item, description, count
   * @param id
   * @param itemInfo
   * @return
   */
  public ItemInfo updateItem(long id, ItemInfo itemInfo)
  {
    String name               = itemInfo.getName();
    String description        = itemInfo.getDescription();
    // Double cost               = itemInfo.getCost();
    Long count                = itemInfo.getCount();
    // Boolean active            = itemInfo.getActive();
    ItemInfoType itemInfoType = itemInfo.getType();

    Optional<Item> optional = this.itemRepository.findById(id);

    if (optional.isPresent())
    {
      // Item found, Update the record
      Item item = optional.get();

      if (name != null)
        item.setName(name);

      if (description != null)
        item.setDescription(description);

      // if (cost != null)
      //   item.setCost(cost);

      if (count != null)
        item.setCount(count);

      // if (active != null)
      //   item.setActive(active ? 1 : 0);

      item.setModifiedDate(new Date());

      if (itemInfoType != null && itemInfoType.getId() != null)
      {
        ItemType itemType = new ItemType(itemInfoType.getId());
        item.setType(itemType);
      }

      ModelConverter<ItemInfo, Item> modelConverter = new ItemModelConverter();
      return modelConverter.convertToResourceModel(this.itemRepository.save(item));
    }
    else
    {
      // Throw exception. Update failed, Item not found
      throw new IllegalArgumentException("Update failed. Item not found.");
    }
  }

  /**
   * Delete an item.
   * @param id
   */
  public boolean deleteItem(long id)
  {
    try
    {
      this.itemRepository.deleteById(id);
      return true;
    }
    catch (EmptyResultDataAccessException e)
    {
      // Deletion failed.
      logger.info(String.format("Delete failed : %s", e.getMessage()));
      return false;
    }
  }

  /**
   * Activate the item.
   * @param id
   * @return
   */
  public void activateItem(long id)
  {
    Optional<Item> optional = this.itemRepository.findById(id);

    if (optional.isPresent())
    {
      // Item found, Update the record
      Item item = optional.get();
      item.setActive(1);
      item.setModifiedDate(new Date());
      this.itemRepository.save(item);
    }
    else
    {
      // Throw exception. Update failed, Item not found
      throw new IllegalArgumentException("Failed to activate the item. Item not found.");
    }
  }

  /**
   * Deactivate the item.
   * @param id
   * @return
   */
  public void deactivateItem(long id)
  {
    Optional<Item> optional = this.itemRepository.findById(id);

    if (optional.isPresent())
    {
      // Item found, Update the record
      Item item = optional.get();
      item.setActive(0);
      item.setModifiedDate(new Date());
      this.itemRepository.save(item);
    }
    else
    {
      // Throw exception. Update failed, Item not found
      throw new IllegalArgumentException("Failed to deactivate the item. Item not found.");
    }
  }

  /**
   * Get the total count of the items.
   * By default, it returns the total count of all the items.
   * @param typeId (Optional) The type of the item.
   * @return
   */
  public Long getTotalCountOfItems(Long typeId)
  {
    if (typeId == null)
    {
      // Return the sum of all the item count
      Long totalItemCount = this.itemRepository.getSumOfItemCount();
      return totalItemCount != null ? totalItemCount : 0;
    }
    else
    {
      // Return the sum of the item count by type
      Long totalItemCount = this.itemRepository.getSumOfItemCountByType(typeId);

      if (totalItemCount != null)
        return totalItemCount;
      else
        // Throw exception. Invalid typeId.
        throw new IllegalArgumentException("Invalid value for [typeId]");
    }
  }

  /**
   * Get the total cost of the items.
   * By default, it returns the total cost of all the items.
   * @param typeId (Optional) The type of the item.
   * @return
   */
  public Double getTotalCostOfItems(Long typeId)
  {
    if (typeId == null)
    {
      // Return the sum of all the item cost
      Double totalItemCost = this.itemRepository.getSumOfCost();
      return totalItemCost != null ? totalItemCost : 0.00;
    }
    else
    {
      // Return the sum of the item cost by type
      Double totalItemCost = this.itemRepository.getSumOfCostByType(typeId);

      if (totalItemCost != null)
        return totalItemCost;
      else
        // Throw exception. Invalid typeId.
        throw new IllegalArgumentException("Invalid value for [typeId]");
    }
  }
}
