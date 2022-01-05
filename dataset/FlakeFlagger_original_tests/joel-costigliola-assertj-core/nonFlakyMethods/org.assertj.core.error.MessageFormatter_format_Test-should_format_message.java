@Test public void should_format_message(){
  Description description=new TextDescription("Test");
  Representation representation=new StandardRepresentation();
  String s=messageFormatter.format(description,representation,"Hello %s","World");
  assertEquals("[Test] Hello \"World\"",s);
  verify(descriptionFormatter).format(description);
}
