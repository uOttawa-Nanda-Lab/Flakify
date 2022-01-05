@Test public void should_create_error_message(){
  String message=factory.create(new TestDescription("Test"),new StandardRepresentation());
  assertEquals("[Test] \nExpecting\n <\"Yoda\">\nto have the same class as:\n <10L>(<java.lang.Long>) \nbut its class was:<java.lang.String>",message);
}
