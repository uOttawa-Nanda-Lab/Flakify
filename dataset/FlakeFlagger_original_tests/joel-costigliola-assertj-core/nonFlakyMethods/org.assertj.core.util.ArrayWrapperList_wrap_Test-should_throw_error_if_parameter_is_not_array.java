@Test public void should_throw_error_if_parameter_is_not_array(){
  thrown.expectIllegalArgumentException("The object to wrap should be an array");
  ArrayWrapperList.wrap("Yoda");
}
