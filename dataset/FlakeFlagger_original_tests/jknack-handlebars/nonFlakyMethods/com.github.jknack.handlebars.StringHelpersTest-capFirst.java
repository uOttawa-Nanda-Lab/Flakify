@Test public void capFirst() throws IOException {
  Options options=createMock(Options.class);
  replay(options);
  assertEquals("capitalizeFirst",capitalizeFirst.name());
  assertEquals("Handlebars.java",capitalizeFirst.apply("handlebars.java",options));
  verify(options);
}
