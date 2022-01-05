@Test public void camelize() throws IOException {
  assertEquals("ThisIsCamelCase",handlebars.compile("{{camelize this}}").apply("This is camel case"));
  assertEquals("thisIsCamelCase",handlebars.compile("{{camelize this capFirst=false}}").apply("This is camel case"));
}
