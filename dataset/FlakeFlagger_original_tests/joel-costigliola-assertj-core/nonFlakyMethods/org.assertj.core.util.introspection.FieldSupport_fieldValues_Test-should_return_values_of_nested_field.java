@Test public void should_return_values_of_nested_field(){
  Iterable<String> firstNames=fieldSupport.fieldValues("name.first",String.class,employees);
  assertEquals(newArrayList("Yoda","Luke"),firstNames);
}
