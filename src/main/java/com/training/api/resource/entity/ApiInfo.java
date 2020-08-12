package com.training.api.resource.entity;

/**
 * @since   2020-08-12
 * @author  Andrew Ray Abad
 */
public class ApiInfo
{
  private final String name;
  private final String version;

  public ApiInfo(String name, String version)
  {
    this.name = name;
    this.version = version;
  }

  public String getName()
  {
    return name;
  }

  public String getVersion()
  {
    return version;
  }
}
