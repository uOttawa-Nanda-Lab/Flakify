@Test public void should_create_error_message(){
  Map<?,?> map=mapOf(entry("name","Yoda"),entry("color","green"));
  ErrorMessageFactory factory=shouldContainKeys(map,newLinkedHashSet("name"));
  String message=factory.create(new TextDescription("Test"),new StandardRepresentation());
  assertThat(message).isEqualTo("[Test] \nExpecting:\n <{\"name\"=\"Yoda\", \"color\"=\"green\"}>\nto contain key:\n <\"name\">");
}
