@Test public void should_return_same_description(){
  Description e=new TextDescription("Yoda");
  Description d=DescriptionValidations.checkIsNotNull(e);
  assertSame(e,d);
}
