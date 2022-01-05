@Test public void should_create_error_message(){
  String message=factory.create(new TestDescription("Test"),new StandardRepresentation());
  assertEquals("[Test] \nExpecting empty but was:<[\"Luke\", \"Yoda\"]>",message);
}
