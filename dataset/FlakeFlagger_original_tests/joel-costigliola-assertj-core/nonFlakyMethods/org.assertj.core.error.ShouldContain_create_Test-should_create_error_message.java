@Test public void should_create_error_message(){
  String message=factory.create(new TextDescription("Test"),new StandardRepresentation());
  assertEquals("[Test] \nExpecting:\n <[\"Yoda\"]>\nto contain:\n <[\"Luke\", \"Yoda\"]>\nbut could not find:\n" + " <[\"Luke\"]>\n",message);
}
