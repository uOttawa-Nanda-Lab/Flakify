@Test public void should_create_error_message_when_ignoring_case(){
  factory=shouldContainIgnoringCase("Yoda","Luke");
  String message=factory.create(new TextDescription("Test"),new StandardRepresentation());
  assertEquals("[Test] \nExpecting:\n <\"Yoda\">\nto contain:\n <\"Luke\">\n (ignoring case)",message);
}
