@Test public void slugify() throws IOException {
  assertEquals("hablo-espanol",handlebars.compile("{{slugify this}}").apply("Hablo español"));
}
