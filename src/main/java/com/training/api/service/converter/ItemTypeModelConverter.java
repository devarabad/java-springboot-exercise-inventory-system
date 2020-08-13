package com.training.api.service.converter;

import com.training.api.data.model.ItemType;
import com.training.api.resource.entity.ItemTypeInfo;

import java.util.Date;

/**
 * @since   2020-08-12
 * @author  Andrew Ray Abad
 */
public class ItemTypeModelConverter extends ModelConverter<ItemTypeInfo,ItemType>
{
  @Override
  public ItemTypeInfo convertToResourceModel(ItemType itemType)
  {
    if (itemType == null)
      return null;

    long id             = itemType.getId();
    String name         = itemType.getName();
    String description  = itemType.getDescription();
    double cost         = itemType.getCost();
    boolean active      = itemType.getActive() >= 1;
    Date createdDate    = itemType.getCreatedDate();
    Date modifiedDate   = itemType.getModifiedDate();

    return new ItemTypeInfo(id
                            , name
                            , description
                            , cost
                            , active
                            , createdDate
                            , modifiedDate);
  }
}
