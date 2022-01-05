@Test public void metricPrefix() throws IOException {
  assertEquals("200",handlebars.compile("{{metricPrefix this}}").apply(200));
  assertEquals("1k",handlebars.compile("{{metricPrefix this}}").apply(1000));
  assertEquals("3.5M",handlebars.compile("{{metricPrefix this}}").apply(3500000));
}
