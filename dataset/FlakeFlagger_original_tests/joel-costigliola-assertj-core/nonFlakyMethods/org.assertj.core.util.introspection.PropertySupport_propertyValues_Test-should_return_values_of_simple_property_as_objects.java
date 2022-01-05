@Test public void should_return_values_of_simple_property_as_objects(){
  Iterable<Integer> ages=PropertySupport.instance().propertyValues("age",Integer.class,employees);
  Iterable<Object> agesAsObjects=PropertySupport.instance().propertyValues("age",employees);
  assertEquals(agesAsObjects,ages);
  Iterable<String> firstNames=PropertySupport.instance().propertyValues("name.first",String.class,employees);
  Iterable<Object> firstNamesAsObjects=PropertySupport.instance().propertyValues("name.first",employees);
  assertEquals(firstNamesAsObjects,firstNames);
}
