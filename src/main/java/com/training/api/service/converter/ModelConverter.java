package com.training.api.service.converter;

import java.util.LinkedList;
import java.util.List;

/**
 * @since   2020-08-12
 * @author  Andrew Ray Abad
 */
public abstract
  class       ModelConverter<ResourceModel, DataModel>
  implements  IModelConverter<ResourceModel, DataModel>
{
  @Override
  public abstract ResourceModel convertToResourceModel(DataModel o);

  /**
   * Converts the DataModel List to ResourceModel List
   * @param dataModelList
   * @return
   */
  public List<ResourceModel> convertToResourceModelList(List<DataModel> dataModelList)
  {
    if (dataModelList == null)
    {
      return null;
    }

    List<ResourceModel> resourceModelList = new LinkedList<>();

    dataModelList.forEach(dataModel ->
      {
        resourceModelList.add(convertToResourceModel(dataModel));
      });

    return resourceModelList;
  }

  /**
   * Converts the DataModel List to ResourceModel List
   * @param dataModelIterable
   * @return
   */
  public List<ResourceModel> convertToResourceModelList(Iterable<DataModel> dataModelIterable)
  {
    if (dataModelIterable == null)
    {
      return null;
    }

    List<ResourceModel> resourceModelList = new LinkedList<>();

    dataModelIterable.forEach(dataModel ->
    {
      resourceModelList.add(convertToResourceModel(dataModel));
    });

    return resourceModelList;
  }
}
