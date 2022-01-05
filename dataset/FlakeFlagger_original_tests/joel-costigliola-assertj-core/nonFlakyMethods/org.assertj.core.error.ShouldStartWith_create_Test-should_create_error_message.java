@Test public void should_create_error_message(){
  String message=factory.create(new TextDescription("Test"),new StandardRepresentation());
  assertEquals("[Test] \nExpecting:\n <[\"Yoda\", \"Luke\"]>\nto start with:\n <[\"Han\", \"Leia\"]>\n",message);
}
