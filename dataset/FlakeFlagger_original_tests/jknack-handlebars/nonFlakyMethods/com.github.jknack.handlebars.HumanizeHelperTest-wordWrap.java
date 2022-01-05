@Test public void wordWrap() throws IOException {
  assertEquals("Handlebars.java",handlebars.compile("{{wordWrap this 14}}").apply("Handlebars.java rock"));
}
