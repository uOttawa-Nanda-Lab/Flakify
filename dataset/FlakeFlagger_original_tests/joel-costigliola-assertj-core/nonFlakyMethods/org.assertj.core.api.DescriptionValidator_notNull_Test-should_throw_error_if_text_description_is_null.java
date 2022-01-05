@Test public void should_throw_error_if_text_description_is_null(){
  thrown.expectNullPointerException(descriptionIsNull());
  String d=null;
  DescriptionValidations.checkIsNotNull(d);
}
