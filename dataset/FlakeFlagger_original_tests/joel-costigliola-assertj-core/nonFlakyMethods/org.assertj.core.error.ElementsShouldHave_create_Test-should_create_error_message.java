@Test public void should_create_error_message(){
  String message=factory.create(new TextDescription("Test"),new StandardRepresentation());
  assertEquals("[Test] \nExpecting elements:\n<[\"Leia\"]>\n of \n<[\"Yoda\", \"Luke\", \"Leia\"]>\n to have <jedi power>",message);
}
