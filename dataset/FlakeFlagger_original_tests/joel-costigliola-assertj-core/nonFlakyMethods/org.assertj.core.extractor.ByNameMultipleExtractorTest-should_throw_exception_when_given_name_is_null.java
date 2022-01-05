@Test public void should_throw_exception_when_given_name_is_null(){
  thrown.expectIllegalArgumentException("The names of the fields/properties to read should not be null");
  new ByNameMultipleExtractor<Employee>((String[])null).extract(yoda);
}
