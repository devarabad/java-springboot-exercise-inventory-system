package com.training.api.data.repository;

import com.training.api.data.model.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @since   2020-08-12
 * @author  Andrew Ray Abad
 */
@Repository
public interface ItemRepository extends CrudRepository<Item, Long>
{
  @Query(value = "SELECT SUM(ITEM_COUNT) FROM ITEM", nativeQuery = true)
  public Long getSumOfItemCount();

  @Query(value = "SELECT SUM(item.ITEM_COUNT) FROM ITEM item WHERE item.TYPE_ID = ?1", nativeQuery = true)
  public Long getSumOfItemCountByType(long typeId);

  @Query(value = "SELECT ROUND(SUM(COST), 2) FROM ITEM", nativeQuery = true)
  public Double getSumOfCost();

  @Query(value = "SELECT ROUND(SUM(item.COST), 2) FROM ITEM item WHERE item.TYPE_ID = ?1", nativeQuery = true)
  public Double getSumOfCostByType(long typeId);
}
