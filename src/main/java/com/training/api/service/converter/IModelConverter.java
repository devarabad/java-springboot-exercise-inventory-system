package com.training.api.service.converter;

/**
 * @since   2020-08-12
 * @author  Andrew Ray Abad
 */
public interface IModelConverter<ResourceModel, DataModel>
{
  public ResourceModel convertToResourceModel(DataModel dataModel);
}
