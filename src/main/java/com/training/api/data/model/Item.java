package com.training.api.data.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @since   2020-08-12
 * @author  Andrew Ray Abad
 */
@Entity
@Table(name = "ITEM")
public class Item
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private long id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "COST")
  private double cost;

  @Column(name = "ITEM_COUNT")
  private long count;

  @Column(name = "ACTIVE")
  private int active;

  @Column(name = "CREATED_DATE")
  private Date createdDate;

  @Column(name = "MODIFIED_DATE")
  private Date modifiedDate;

  @ManyToOne
  @JoinColumn(name = "TYPE_ID")
  private ItemType type;

  public Item()
  {

  }

  public Item(long id)
  {
    this.id = id;
  }

  public long getId()
  {
    return id;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public double getCost()
  {
    return cost;
  }

  public void setCost(double cost)
  {
    this.cost = cost;
  }

  public long getCount()
  {
    return count;
  }

  public void setCount(long count)
  {
    this.count = count;
  }

  public int getActive()
  {
    return active;
  }

  public void setActive(int active)
  {
    this.active = active;
  }

  public Date getCreatedDate()
  {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate)
  {
    this.createdDate = createdDate;
  }

  public Date getModifiedDate()
  {
    return modifiedDate;
  }

  public void setModifiedDate(Date modifiedDate)
  {
    this.modifiedDate = modifiedDate;
  }

  public ItemType getType()
  {
    return type;
  }

  public void setType(ItemType type)
  {
    this.type = type;
  }
}