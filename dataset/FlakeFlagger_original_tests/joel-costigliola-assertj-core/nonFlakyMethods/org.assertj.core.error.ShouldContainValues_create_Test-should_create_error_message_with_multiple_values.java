@Test public void should_create_error_message_with_multiple_values(){
  Map<?,?> map=mapOf(entry("name","Yoda"),entry("color","green"));
  ErrorMessageFactory factory=shouldContainValues(map,newLinkedHashSet("VeryOld","Vader"));
  String message=factory.create(new TextDescription("Test"),new StandardRepresentation());
  assertThat(message).isEqualTo("[Test] \n" + "Expecting:\n" + "  <{\"name\"=\"Yoda\", \"color\"=\"green\"}>\n"+ "to contain values:\n"+ "  <[\"VeryOld\", \"Vader\"]>");
}
