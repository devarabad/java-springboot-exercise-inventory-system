package com.training.api.service;

import com.training.api.service.converter.ModelConverter;
import com.training.api.data.model.ItemType;
import com.training.api.data.repository.ItemTypeRepository;
import com.training.api.resource.entity.ItemTypeInfo;
import com.training.api.service.converter.ItemTypeModelConverter;
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
public class ItemTypeService
{
  private final Logger logger = Logger.getLogger(getClass().getName());

  private final ItemTypeRepository itemTypeRepository;

  public ItemTypeService(ItemTypeRepository itemTypeRepository)
  {
    this.itemTypeRepository = itemTypeRepository;
  }

  /**
   * Get all item type.
   * @return
   */
  public List<ItemTypeInfo> getAllItemType()
  {
    Iterable<ItemType> itemTypeList                       = this.itemTypeRepository.findAll();
    ModelConverter<ItemTypeInfo, ItemType> modelConverter = new ItemTypeModelConverter();
    return modelConverter.convertToResourceModelList(itemTypeList);
  }

  /**
   * Get an item type.
   * @param id
   * @return
   */
  public ItemTypeInfo getItemType(long id)
  {
    Optional<ItemType> optional = this.itemTypeRepository.findById(id);

    if (optional.isPresent())
    {
      ModelConverter<ItemTypeInfo, ItemType> modelConverter = new ItemTypeModelConverter();
      return modelConverter.convertToResourceModel(optional.get());
    }
    else
    {
      // Throw exception. Update failed, Item not found
      throw new IllegalArgumentException("Item not found.");
    }
  }

  /**
   * Add an item type.
   * @param itemTypeInfo
   * @return
   */
  public ItemTypeInfo addItemType(ItemTypeInfo itemTypeInfo)
  {
    ItemType itemType = new ItemType();
    itemType.setName(itemTypeInfo.getName());
    itemType.setDescription(itemTypeInfo.getDescription());
    itemType.setCost(itemTypeInfo.getCost() != null ? itemTypeInfo.getCost() : 0.00);
    itemType.setActive(itemTypeInfo.getActive() != null && itemTypeInfo.getActive() ? 1 : 0);
    itemType.setCreatedDate(new Date());

    ModelConverter<ItemTypeInfo, ItemType> modelConverter = new ItemTypeModelConverter();
    return modelConverter.convertToResourceModel(this.itemTypeRepository.save(itemType));
  }

  /**
   * Update an item type.
   * @param id
   * @param itemTypeInfo
   * @return
   */
  public ItemTypeInfo updateItemType(long id, ItemTypeInfo itemTypeInfo)
  {
    String name         = itemTypeInfo.getName();
    String description  = itemTypeInfo.getDescription();
    Double cost         = itemTypeInfo.getCost();
    Boolean active      = itemTypeInfo.getActive();

    Optional<ItemType> optional = this.itemTypeRepository.findById(id);

    if (optional.isPresent())
    {
      // ItemType found, Update the record
      ItemType itemType = optional.get();

      if (name != null)
        itemType.setName(name);

      if (description != null)
        itemType.setDescription(description);

      if (cost != null)
        itemType.setCost(cost);

      if (active != null)
        itemType.setActive(active ? 1 : 0);

      itemType.setModifiedDate(new Date());

      ModelConverter<ItemTypeInfo, ItemType> modelConverter = new ItemTypeModelConverter();
      return modelConverter.convertToResourceModel(this.itemTypeRepository.save(itemType));
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
  public boolean deleteItemType(long id)
  {
    try
    {
      this.itemTypeRepository.deleteById(id);
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
   */
  public void activateItemType(long id)
  {
    Optional<ItemType> optional = this.itemTypeRepository.findById(id);

    if (optional.isPresent())
    {
      // ItemType found, Update the record
      ItemType itemType = optional.get();
      itemType.setActive(1);
      itemType.setModifiedDate(new Date());
      this.itemTypeRepository.save(itemType);
    }
    else
    {
      // Throw exception. Update failed, Item not found
      throw new IllegalArgumentException("Failed to activate the item type. Item type not found.");
    }
  }

  /**
   * Deactivate the item.
   * @param id
   */
  public void deactivateItemType(long id)
  {
    Optional<ItemType> optional = this.itemTypeRepository.findById(id);

    if (optional.isPresent())
    {
      // ItemType found, Update the record
      ItemType itemType = optional.get();
      itemType.setActive(0);
      itemType.setModifiedDate(new Date());
      this.itemTypeRepository.save(itemType);
    }
    else
    {
      // Throw exception. Update failed, Item not found
      throw new IllegalArgumentException("Failed to deactivate the item. Item not found.");
    }
  }
}
