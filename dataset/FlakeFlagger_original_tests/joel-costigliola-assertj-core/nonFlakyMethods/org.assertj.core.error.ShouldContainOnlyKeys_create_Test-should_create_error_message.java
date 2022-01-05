@Test public void should_create_error_message(){
  ErrorMessageFactory factory=shouldContainOnlyKeys(mapOf(entry("name","Yoda"),entry("color","green")),newArrayList("jedi","color"),newLinkedHashSet("jedi"),newLinkedHashSet("name"));
  String message=factory.create(new TextDescription("Test"),new StandardRepresentation());
  assertThat(message).isEqualTo("[Test] \n" + "Expecting:\n" + "  <{\"name\"=\"Yoda\", \"color\"=\"green\"}>\n"+ "to contain only following keys:\n"+ "  <[\"jedi\", \"color\"]>\n"+ "keys not found:\n"+ "  <[\"jedi\"]>\n"+ "and keys not expected:\n"+ "  <[\"name\"]>\n");
}
