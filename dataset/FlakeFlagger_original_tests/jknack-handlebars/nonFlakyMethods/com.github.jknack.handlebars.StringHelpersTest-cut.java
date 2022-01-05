@Test public void cut() throws IOException {
  Options options=createMock(Options.class);
  expect(options.param(0," ")).andReturn(" ");
  replay(options);
  assertEquals("cut",cut.name());
  assertEquals("handlebars.java",cut.apply("handle bars .  java",options));
  verify(options);
}
