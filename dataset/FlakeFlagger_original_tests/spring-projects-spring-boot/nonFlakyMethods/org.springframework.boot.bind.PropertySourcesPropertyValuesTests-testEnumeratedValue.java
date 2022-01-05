@Test public void testEnumeratedValue(){
  PropertySourcesPropertyValues propertyValues=new PropertySourcesPropertyValues(this.propertySources);
  assertEquals("bar",propertyValues.getPropertyValue("name").getValue());
}
