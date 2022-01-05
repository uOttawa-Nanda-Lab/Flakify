@Test public void should_return_value(){
  TextDescription description=new TextDescription("Flash");
  assertEquals(description.value,description.toString());
}
