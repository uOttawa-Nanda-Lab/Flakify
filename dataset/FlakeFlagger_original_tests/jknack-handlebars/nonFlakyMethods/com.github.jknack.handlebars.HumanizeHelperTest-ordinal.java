@Test public void ordinal() throws IOException {
  assertEquals("1st",handlebars.compile("{{ordinal this locale=\"en_US\"}}").apply(1));
  assertEquals("2nd",handlebars.compile("{{ordinal this locale=\"en_US\"}}").apply(2));
  assertEquals("3rd",handlebars.compile("{{ordinal this locale=\"en_US\"}}").apply(3));
  assertEquals("10th",handlebars.compile("{{ordinal this locale=\"en_US\"}}").apply(10));
}
