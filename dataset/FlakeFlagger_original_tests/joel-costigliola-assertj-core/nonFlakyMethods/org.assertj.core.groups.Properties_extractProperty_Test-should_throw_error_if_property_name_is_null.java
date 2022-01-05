@Test public void should_throw_error_if_property_name_is_null(){
  thrown.expectNullPointerException("The name of the property to read should not be null");
  Properties.extractProperty(null,Object.class);
}
