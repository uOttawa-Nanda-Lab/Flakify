@Test public void should_create_error_message_differentiating_long_from_integer(){
  factory=shouldContain(5L,5,5);
  String message=factory.create(new TextDescription("Test"),new StandardRepresentation());
  assertEquals("[Test] \nExpecting:\n <5L>\nto contain:\n <5>\nbut could not find:\n" + " <5>\n",message);
}
