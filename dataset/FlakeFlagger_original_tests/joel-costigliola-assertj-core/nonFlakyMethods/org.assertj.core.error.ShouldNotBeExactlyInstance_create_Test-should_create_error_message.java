@Test public void should_create_error_message(){
  String message=factory.create(new TestDescription("Test"),new StandardRepresentation());
  assertEquals("[Test] \nExpecting\n <\"Yoda\">\nnot to be of exact type:\n <java.io.File>\nbut was:<java.lang.String>",message);
}
