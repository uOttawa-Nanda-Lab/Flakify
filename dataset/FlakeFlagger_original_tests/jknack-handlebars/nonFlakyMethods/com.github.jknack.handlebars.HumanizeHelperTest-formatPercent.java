@Test public void formatPercent() throws IOException {
  assertEquals("50%",handlebars.compile("{{formatPercent this}}").apply(0.5));
  assertEquals("100%",handlebars.compile("{{formatPercent this}}").apply(1));
  assertEquals("56%",handlebars.compile("{{formatPercent this}}").apply(0.564));
}
