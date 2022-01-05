@Test public void slugify() throws IOException {
  Options options=createMock(Options.class);
  replay(options);
  assertEquals("slugify",slugify.name());
  assertEquals("joel-is-a-slug",slugify.apply("  Joel is a slug  ",options));
  verify(options);
}
