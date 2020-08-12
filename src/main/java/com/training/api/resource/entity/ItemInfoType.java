package com.training.api.resource.entity;

/**
 * @since   2020-08-12
 * @author  Andrew Ray Abad
 */
public class ItemInfoType
{
  private Long    id;
  private String  name;

  protected ItemInfoType() {}

  public ItemInfoType(Long id, String name)
  {
    this.id   = id;
    this.name = name;
  }

  public Long getId()
  {
    return id;
  }

  public String getName()
  {
    return name;
  }

  public static class ItemInfoTypeBuilder
  {
    private final Long  id;
    private String      name;

    public ItemInfoTypeBuilder(Long id)
    {
      this.id = id;
    }

    public ItemInfoTypeBuilder name(String name) {
      this.name = name;
      return this;
    }

    public ItemInfoType build() {
      return new ItemInfoType(id, name);
    }
  }
}
