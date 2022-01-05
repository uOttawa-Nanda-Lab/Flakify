@Test public void should_return_description_with_given_text(){
  Description d=DescriptionValidations.checkIsNotNull("Yoda");
  assertEquals("Yoda",d.value());
}
