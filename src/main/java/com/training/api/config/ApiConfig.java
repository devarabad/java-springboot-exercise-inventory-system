package com.training.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @since   2020-08-12
 * @author  Andrew Ray Abad
 */
@Configuration
@ConfigurationProperties("api")
public class ApiConfig
{
  private String name;
  private String version;

  public ApiConfig()
  {
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public void setVersion(String version)
  {
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
