@Test public void should_return_formatted_value(){
  TextDescription description=new TextDescription("Robin %s","Hood");
  assertEquals("Robin Hood",description.value());
}
