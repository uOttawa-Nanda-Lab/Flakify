@Test public void should_throw_exception_when_given_name_is_empty(){
  thrown.expectIllegalArgumentException("The names of the fields/properties to read should not be empty");
  new ByNameMultipleExtractor<Employee>(new String[0]).extract(yoda);
}
