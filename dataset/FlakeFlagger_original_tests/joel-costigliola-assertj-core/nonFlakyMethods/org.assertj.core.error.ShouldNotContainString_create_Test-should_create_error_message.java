@Test public void should_create_error_message(){
  ErrorMessageFactory factory=shouldNotContain("Yoda","od");
  String message=factory.create(new TextDescription("Test"),new StandardRepresentation());
  assertEquals("[Test] \nExpecting:\n <\"Yoda\">\nnot to contain:\n <\"od\"> ",message);
}
