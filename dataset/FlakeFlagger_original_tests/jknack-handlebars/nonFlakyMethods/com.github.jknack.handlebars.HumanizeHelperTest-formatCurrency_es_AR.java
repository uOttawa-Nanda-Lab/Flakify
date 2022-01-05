/** 
 * Note: beside locale is optional it must be set in unit testing, otherwise the test might fail in a different machine.
 * @throws IOException
 */
@Test public void formatCurrency_es_AR() throws IOException {
  assertEquals("$34",handlebars.compile("{{formatCurrency this locale=\"es_AR\"}}").apply(34));
  assertEquals("$1.000",handlebars.compile("{{formatCurrency this locale=\"es_AR\"}}").apply(1000));
  assertEquals("$12,50",handlebars.compile("{{formatCurrency this locale=\"es_AR\"}}").apply(12.5));
}
