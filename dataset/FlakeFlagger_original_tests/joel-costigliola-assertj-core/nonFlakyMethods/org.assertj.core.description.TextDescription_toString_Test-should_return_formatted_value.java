@Test public void should_return_formatted_value(){
  TextDescription description=new TextDescription("Flash %s {}","MacQueen");
  assertEquals("Flash MacQueen {}",description.toString());
}
