@Test public void should_create_error_message() throws Exception {
  assertThat(createMessage()).isEqualTo(String.format("[TEST] %n" + "Expecting%n" + "  <" + actual + ">%n"+ "to have name:%n"+ "  <\""+ expectedName+ "\">%n"+ "but had:%n"+ "  <\"actual-file\">"));
}
