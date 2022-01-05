@Test public void testNonEnumeratedValue(){
  PropertySourcesPropertyValues propertyValues=new PropertySourcesPropertyValues(this.propertySources);
  assertEquals("bar",propertyValues.getPropertyValue("foo").getValue());
}
