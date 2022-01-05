@Test public void lower() throws IOException {
  Options options=createMock(Options.class);
  replay(options);
  assertEquals("lower",lower.name());
  assertEquals("handlebars.java",lower.apply("Handlebars.java",options));
  verify(options);
}
