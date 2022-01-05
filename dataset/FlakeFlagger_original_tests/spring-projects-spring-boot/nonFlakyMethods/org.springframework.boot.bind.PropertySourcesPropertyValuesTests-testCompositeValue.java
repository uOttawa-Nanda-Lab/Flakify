@Test public void testCompositeValue(){
  PropertySource<?> map=this.propertySources.get("map");
  CompositePropertySource composite=new CompositePropertySource("composite");
  composite.addPropertySource(map);
  this.propertySources.replace("map",composite);
  PropertySourcesPropertyValues propertyValues=new PropertySourcesPropertyValues(this.propertySources);
  assertEquals("bar",propertyValues.getPropertyValue("foo").getValue());
}
