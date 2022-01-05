@Test public void should_create_error_message_with_multiple_keys(){
  Map<?,?> map=mapOf(entry("name","Yoda"),entry("color","green"));
  ErrorMessageFactory factory=shouldContainKeys(map,newLinkedHashSet("name","color"));
  String message=factory.create(new TextDescription("Test"),new StandardRepresentation());
  assertThat(message).isEqualTo("[Test] \nExpecting:\n <{\"name\"=\"Yoda\", \"color\"=\"green\"}>\nto contain keys:\n <[\"name\", \"color\"]>");
}
