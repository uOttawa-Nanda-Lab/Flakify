@Test public void should_create_error_message(){
  factory=shouldContain("Yoda","Luke");
  String message=factory.create(new TextDescription("Test"),new StandardRepresentation());
  assertEquals("[Test] \nExpecting:\n <\"Yoda\">\nto contain:\n <\"Luke\"> ",message);
}
