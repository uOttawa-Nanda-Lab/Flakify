@Test public void should_return_values_of_simple_field(){
  Iterable<Long> ids=fieldSupport.fieldValues("id",Long.class,employees);
  assertEquals(newArrayList(1L,2L),ids);
}
