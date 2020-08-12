package com.training.api.resource.entity;

import java.util.Date;

/**
 * @since   2020-08-12
 * @author  Andrew Ray Abad
 */
public class ItemTypeInfo
{
  private Long    id;
  private String  name;
  private String  description;
  private Double  cost;
  private Boolean active;
  private Date    createdDate;
  private Date    modifiedDate;

  protected ItemTypeInfo() {}

  public ItemTypeInfo(Long id
                      , String name
                      , String description
                      , Double cost
                      , Boolean active
                      , Date createdDate
                      , Date modifiedDate)
  {
    this.id           = id;
    this.name         = name;
    this.description  = description;
    this.cost         = cost;
    this.active       = active;
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

  public Boolean getActive()
  {
    return active;
  }

  public Date getCreatedDate()
  {
    return createdDate;
  }

  public Date getModifiedDate()
  {
    return modifiedDate;
  }

  public static class ItemTypeInfoBuilder
  {
    private final Long  id;
    private String      name;
    private String      description;
    private Double      cost;
    private Boolean     active;
    private Date        createdDate;
    private Date        modifiedDate;

    public ItemTypeInfoBuilder(Long id)
    {
      this.id = id;
    }

    public ItemTypeInfoBuilder name(String name) {
      this.name = name;
      return this;
    }

    public ItemTypeInfoBuilder description(String description) {
      this.description = description;
      return this;
    }

    public ItemTypeInfoBuilder cost(Double cost) {
      this.cost = cost;
      return this;
    }

    public ItemTypeInfoBuilder active(Boolean active) {
      this.active = active;
      return this;
    }

    public ItemTypeInfoBuilder createdDate(Date createdDate) {
      this.createdDate = createdDate;
      return this;
    }

    public ItemTypeInfoBuilder modifiedDate(Date modifiedDate) {
      this.modifiedDate = modifiedDate;
      return this;
    }

    public ItemTypeInfo build() {
      return new ItemTypeInfo(id
                              , name
                              , description
                              , cost
                              , active
                              , createdDate
                              , modifiedDate);
    }
  }
}
