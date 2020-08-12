package com.training.api.resource.entity;

/**
 * @since   2020-08-12
 * @author  Andrew Ray Abad
 */
public class ResourceMessage
{
  private final String status;
  private final String message;
  private final String code;

  public ResourceMessage( String status
                          , String message
                          , String code)
  {
    this.status   = status;
    this.message  = message;
    this.code     = code;
  }

  public String getStatus()
  {
    return status;
  }

  public String getMessage()
  {
    return message;
  }

  public String getCode()
  {
    return code;
  }

  public static class ResourceMessageBuilder
  {
    private final String  status;
    private final String  message;
    private String        code;

    public ResourceMessageBuilder(String status, String message)
    {
      this.status = status;
      this.message = message;
    }

    public ResourceMessageBuilder code(String code) {
      this.code = code;
      return this;
    }

    public ResourceMessage build() {
      return new ResourceMessage( status
                                  , message
                                  , code);
    }
  }
}
