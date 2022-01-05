@Test public void transliterate() throws IOException {
  assertEquals("Hablo espanol",handlebars.compile("{{transliterate this}}").apply("Hablo español"));
}
