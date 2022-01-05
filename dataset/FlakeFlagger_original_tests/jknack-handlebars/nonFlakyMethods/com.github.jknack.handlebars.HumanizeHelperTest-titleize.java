@Test public void titleize() throws IOException {
  assertEquals("Handlebars.java Rocks!",handlebars.compile("{{titleize this}}").apply("Handlebars.java rocks!"));
}
