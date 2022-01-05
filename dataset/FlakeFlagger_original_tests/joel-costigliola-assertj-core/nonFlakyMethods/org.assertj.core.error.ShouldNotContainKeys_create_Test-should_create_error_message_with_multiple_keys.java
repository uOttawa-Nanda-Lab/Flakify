@Test public void should_create_error_message_with_multiple_keys(){
  ErrorMessageFactory factory=shouldNotContainKeys(map,newLinkedHashSet("name","color"));
  String message=factory.create(new TextDescription("Test"),new StandardRepresentation());
  assertThat(message).isEqualTo("[Test] \n" + "Expecting:\n" + "  <{\"name\"=\"Yoda\", \"color\"=\"green\"}>\n"+ "not to contain keys:\n"+ "  <[\"name\", \"color\"]>");
}
