package com.training.api.data.repository;

import com.training.api.data.model.ItemType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @since   2020-08-12
 * @author  Andrew Ray Abad
 */
@Repository
public interface ItemTypeRepository extends CrudRepository<ItemType, Long>
{

}
