package com.training.api.resource;

import com.training.api.config.ApiConfig;
import com.training.api.resource.entity.ApiInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @since   2020-08-12
 * @author  Andrew Ray Abad
 */
@RestController
@RequestMapping(path = "/")
public class ApplicationResource
{
  @Autowired
  private ApiConfig apiConfig;

  public ApplicationResource(ApiConfig apiConfig)
  {
    this.apiConfig = apiConfig;
  }

  @GetMapping
  public ApiInfo getApiInfo()
  {
    String name     = apiConfig.getName();
    String version  = apiConfig.getVersion();
    return new ApiInfo(name, version);
  }
}
