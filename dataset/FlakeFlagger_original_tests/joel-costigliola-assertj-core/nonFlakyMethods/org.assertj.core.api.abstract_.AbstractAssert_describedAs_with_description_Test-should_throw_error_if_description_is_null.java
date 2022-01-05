@Test public void should_throw_error_if_description_is_null(){
  thrown.expectNullPointerException(descriptionIsNull());
  assertions.describedAs((Description)null);
}
