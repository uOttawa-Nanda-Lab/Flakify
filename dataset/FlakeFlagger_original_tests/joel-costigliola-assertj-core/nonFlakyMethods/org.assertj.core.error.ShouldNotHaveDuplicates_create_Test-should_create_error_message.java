@Test public void should_create_error_message(){
  String message=factory.create(new TextDescription("Test"),new StandardRepresentation());
  assertEquals("[Test] \nFound duplicate(s):\n <[\"Yoda\"]>\nin:\n <[\"Yoda\", \"Yoda\", \"Luke\"]>\n",message);
}
