@Test public void should_create_shouldBeInstanceButWasNull_error_message(){
  factory=shouldBeInstanceButWasNull("other",File.class);
  String message=factory.create(new TestDescription("Test"),new StandardRepresentation());
  assertEquals("[Test] \nExpecting object:\n \"other\"\nto be an instance of:\n <java.io.File>\nbut was null",message);
}
