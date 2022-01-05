@Test public void should_throw_exception_when_given_name_is_null(){
  thrown.expectIllegalArgumentException("The name of the field/property to read should not be null");
  new ByNameSingleExtractor<Employee>(null).extract(yoda);
}
