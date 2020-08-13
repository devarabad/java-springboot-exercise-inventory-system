package com.training.api.service.converter;

import com.training.api.data.model.Item;
import com.training.api.resource.entity.ItemInfo;
import com.training.api.resource.entity.ItemInfoType;

import java.util.Date;

/**
 * @since   2020-08-12
 * @author  Andrew Ray Abad
 */
public class ItemModelConverter extends ModelConverter<ItemInfo, Item>
{
  @Override
  public ItemInfo convertToResourceModel(Item item)
  {
    if (item == null)
      return null;

    long itemId         = item.getId();
    String name         = item.getName();
    String description  = item.getDescription();
    double cost         = item.getCost();
    long count          = item.getCount();
    boolean active      = item.getActive() >= 1;
    Date createdDate    = item.getCreatedDate();
    Date modifiedDate   = item.getModifiedDate();

    long typeId         = item.getType().getId();
    String typeName     = item.getType().getName();
    ItemInfoType type   = new ItemInfoType
                                .ItemInfoTypeBuilder(typeId)
                                  .name(typeName)
                                  .build();

    return new ItemInfo(itemId
                        , name
                        , description
                        , cost
                        , count
                        , active
                        , type
                        , createdDate
                        , modifiedDate);
  }
}
