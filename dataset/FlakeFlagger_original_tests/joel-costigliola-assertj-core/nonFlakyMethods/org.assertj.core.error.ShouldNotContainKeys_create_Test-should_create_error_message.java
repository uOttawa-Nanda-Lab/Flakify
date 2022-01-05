@Test public void should_create_error_message(){
  ErrorMessageFactory factory=shouldNotContainKeys(map,newLinkedHashSet("age"));
  String message=factory.create(new TextDescription("Test"),new StandardRepresentation());
  assertThat(message).isEqualTo("[Test] \n" + "Expecting:\n" + "  <{\"name\"=\"Yoda\", \"color\"=\"green\"}>\n"+ "not to contain key:\n"+ "  <\"age\">");
}
