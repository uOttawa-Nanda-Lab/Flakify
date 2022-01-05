@Test public void testSize(){
  PropertySourcesPropertyValues propertyValues=new PropertySourcesPropertyValues(this.propertySources);
  assertEquals(1,propertyValues.getPropertyValues().length);
}
