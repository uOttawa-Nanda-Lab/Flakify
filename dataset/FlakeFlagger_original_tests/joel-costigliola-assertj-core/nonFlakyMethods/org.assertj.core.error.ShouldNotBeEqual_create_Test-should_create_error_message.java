@Test public void should_create_error_message(){
  String message=factory.create(new TestDescription("Jedi"),new StandardRepresentation());
  assertEquals("[Jedi] \nExpecting:\n <\"Yoda\">\nnot to be equal to:\n <\"Luke\">\n",message);
}
