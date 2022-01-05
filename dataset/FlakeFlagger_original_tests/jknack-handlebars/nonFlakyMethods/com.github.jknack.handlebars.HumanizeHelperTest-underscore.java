@Test public void underscore() throws IOException {
  assertEquals("Handlebars_Java_rock",handlebars.compile("{{underscore this}}").apply("Handlebars Java rock"));
}
