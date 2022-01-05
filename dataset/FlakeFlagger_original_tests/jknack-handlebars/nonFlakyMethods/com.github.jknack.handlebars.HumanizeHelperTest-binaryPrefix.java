@Test public void binaryPrefix() throws IOException {
  assertEquals("2 bytes",handlebars.compile("{{binaryPrefix this}}").apply(2));
  assertEquals("1.5 kB",handlebars.compile("{{binaryPrefix this}}").apply(1536));
  assertEquals("5 MB",handlebars.compile("{{binaryPrefix this}}").apply(5242880));
}
