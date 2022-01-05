@Test public void should_return_description(){
  String description="do or do not, there is not try";
  condition.description=new TextDescription(description);
  assertEquals(description,condition.description().value());
}
