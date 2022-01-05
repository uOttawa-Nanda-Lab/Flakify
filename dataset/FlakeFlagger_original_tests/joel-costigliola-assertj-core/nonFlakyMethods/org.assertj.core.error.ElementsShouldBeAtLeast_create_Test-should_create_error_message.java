@Test public void should_create_error_message(){
  String message=factory.create(new TextDescription("Test"),new StandardRepresentation());
  assertEquals("[Test] \nExpecting elements:\n<[\"Yoda\", \"Solo\", \"Leia\"]>\n to be at least 2 times <a Jedi>",message);
}
