@Test public void testOverriddenValue(){
  this.propertySources.addFirst(new MapPropertySource("new",Collections.<String,Object>singletonMap("name","spam")));
  PropertySourcesPropertyValues propertyValues=new PropertySourcesPropertyValues(this.propertySources);
  assertEquals("spam",propertyValues.getPropertyValue("name").getValue());
}
