package com.training.api.resource.entity;

import java.util.Date;

/**
 * @since   2020-08-12
 * @author  Andrew Ray Abad
 */
public class ItemInfo
{
  private Long          id;
  private String        name;
  private String        description;
  private Double        cost;
  private Long          count;
  private Boolean       active;
  private ItemInfoType  type;
  private Date          createdDate;
  private Date          modifiedDate;

  protected ItemInfo() {}

  public ItemInfo(Long id
                  , String name
                  , String description
                  , Double cost
                  , Long count
                  , Boolean active
                  , ItemInfoType type
                  , Date createdDate
                  , Date modifiedDate)
  {
    this.id           = id;
    this.name         = name;
    this.description  = description;
    this.cost         = cost;
    this.count        = count;
    this.active       = active;
    this.type         = type;
    this.createdDate  = createdDate;
    this.modifiedDate = modifiedDate;
  }

  public Long getId()
  {
    return id;
  }

  public String getName()
  {
    return name;
  }

  public String getDescription()
  {
    return description;
  }

  public Double getCost()
  {
    return cost;
  }

  public Long getCount()
  {
    return count;
  }

  public Boolean getActive()
  {
    return active;
  }

  public ItemInfoType getType()
  {
    return type;
  }

  public Date getCreatedDate()
  {
    return createdDate;
  }

  public Date getModifiedDate()
  {
    return modifiedDate;
  }
}
