@Test public void should_create_error_message(){
  String message=factory.create(new TestDescription("Test"),new StandardRepresentation());
  assertThat(message).isEqualTo("[Test] \n" + "Expecting:\n" + "  <8.0f>\n"+ "to be close to:\n"+ "  <6.0f>\n"+ "by less than <1.0f> but difference was <2.0f>.\n"+ "(a difference of exactly <1.0f> being considered valid)");
}
