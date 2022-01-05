@Test public void should_set_value(){
  String value=randomText();
  TextDescription description=new TextDescription(value);
  assertEquals(value,description.value);
}
