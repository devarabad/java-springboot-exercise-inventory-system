package com.training.api.data.model;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @since   2020-08-12
 * @author  Andrew Ray Abad
 */
@Entity
@Table(name = "TYPE")
public class ItemType
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

  @Column(name = "ACTIVE")
  private int active;

  @Column(name = "CREATED_DATE")
  private Date createdDate;

  @Column(name = "MODIFIED_DATE")
  private Date modifiedDate;

  // This is just an example of fetching data with lazy/eager loading
  // This is not typically used for large sets of referenced data
  @OneToMany(mappedBy = "type", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Item> items = new LinkedList<>();

  public ItemType()
  {

  }

  public ItemType(long id)
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

  public List<Item> getItems()
  {
    return items;
  }

  public void addItem(Item item)
  {
    this.items.add(item);
  }
}
