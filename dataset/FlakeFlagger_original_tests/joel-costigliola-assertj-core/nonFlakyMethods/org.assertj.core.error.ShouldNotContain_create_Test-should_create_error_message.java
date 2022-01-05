@Test public void should_create_error_message(){
  ErrorMessageFactory factory=shouldNotContain(newArrayList("Yoda"),newArrayList("Luke","Yoda"),newLinkedHashSet("Yoda"));
  String message=factory.create(new TextDescription("Test"),new StandardRepresentation());
  assertThat(message).isEqualTo("[Test] \n" + "Expecting\n" + " <[\"Yoda\"]>\n"+ "not to contain\n"+ " <[\"Luke\", \"Yoda\"]>\n"+ "but found\n"+ " <[\"Yoda\"]>\n");
}
