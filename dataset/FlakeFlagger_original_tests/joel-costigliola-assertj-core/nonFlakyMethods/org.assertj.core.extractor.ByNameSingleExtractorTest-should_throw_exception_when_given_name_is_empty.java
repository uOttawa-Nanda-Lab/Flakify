@Test public void should_throw_exception_when_given_name_is_empty(){
  thrown.expectIllegalArgumentException("The name of the field/property to read should not be empty");
  new ByNameSingleExtractor<Employee>("").extract(yoda);
}
