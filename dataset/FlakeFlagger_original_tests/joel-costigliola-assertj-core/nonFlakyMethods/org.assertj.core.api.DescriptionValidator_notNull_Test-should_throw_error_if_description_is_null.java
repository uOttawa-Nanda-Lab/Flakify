@Test public void should_throw_error_if_description_is_null(){
  thrown.expectNullPointerException(descriptionIsNull());
  Description d=null;
  DescriptionValidations.checkIsNotNull(d);
}
