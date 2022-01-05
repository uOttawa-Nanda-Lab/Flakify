@Test public void should_create_error_message(){
  String message=factory.create(new TextDescription("Test"),new StandardRepresentation());
  assertEquals("[Test] \nExpecting: <[\"Yoda\", \"Leia\"]> have the same generic type as condition <Not a Jedi>",message);
}
