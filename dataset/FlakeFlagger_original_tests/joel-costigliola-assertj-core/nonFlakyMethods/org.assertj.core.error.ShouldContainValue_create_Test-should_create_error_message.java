@Test public void should_create_error_message(){
  Map<?,?> map=mapOf(entry("name","Yoda"),entry("color","green"));
  ErrorMessageFactory factory=shouldContainValue(map,"VeryOld");
  String message=factory.create(new TextDescription("Test"),new StandardRepresentation());
  assertThat(message).isEqualTo("[Test] \n" + "Expecting:\n" + "  <{\"name\"=\"Yoda\", \"color\"=\"green\"}>\n"+ "to contain value:\n"+ "  <\"VeryOld\">");
}
