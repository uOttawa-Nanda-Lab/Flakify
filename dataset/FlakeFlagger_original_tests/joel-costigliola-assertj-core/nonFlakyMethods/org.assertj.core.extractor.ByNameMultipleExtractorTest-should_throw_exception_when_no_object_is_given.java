@Test public void should_throw_exception_when_no_object_is_given() throws Exception {
  thrown.expectIllegalArgumentException("The object to extract fields/properties from should not be null");
  new ByNameMultipleExtractor<Employee>("id","name.first","age").extract(null);
}
