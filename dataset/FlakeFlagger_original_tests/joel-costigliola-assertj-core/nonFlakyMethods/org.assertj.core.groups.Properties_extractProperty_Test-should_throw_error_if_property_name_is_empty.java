@Test public void should_throw_error_if_property_name_is_empty(){
  thrown.expectIllegalArgumentException("The name of the property to read should not be empty");
  Properties.extractProperty("",Object.class);
}
