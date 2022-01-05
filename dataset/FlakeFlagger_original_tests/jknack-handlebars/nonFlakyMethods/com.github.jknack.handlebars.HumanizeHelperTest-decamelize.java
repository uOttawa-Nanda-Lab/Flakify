@Test public void decamelize() throws IOException {
  assertEquals("this Is Camel Case",handlebars.compile("{{decamelize this}}").apply("thisIsCamelCase"));
  assertEquals("This Is Camel Case",handlebars.compile("{{decamelize this}}").apply("ThisIsCamelCase"));
  assertEquals("ThisxIsxCamelxCase",handlebars.compile("{{decamelize this replacement=\"x\"}}").apply("ThisIsCamelCase"));
}
