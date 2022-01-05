@Test public void should_throw_error_of_description_is_null(){
  thrown.expectNullPointerException("The description to set should not be null");
  String description=null;
  condition.as(description);
}
