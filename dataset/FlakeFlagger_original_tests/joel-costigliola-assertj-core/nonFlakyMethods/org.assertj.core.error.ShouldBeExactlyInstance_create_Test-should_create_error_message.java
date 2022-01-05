@Test public void should_create_error_message(){
  String message=factory.create(new TestDescription("Test"),new StandardRepresentation());
  assertEquals("[Test] \nExpecting:\n <\"Yoda\">\nto be exactly an instance of:\n <java.io.File>\nbut was an instance of:\n <java.lang.String>",message);
}
