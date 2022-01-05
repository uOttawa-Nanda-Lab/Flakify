@Test public void stripTagsMultiLine() throws IOException {
  Options options=createMock(Options.class);
  replay(options);
  assertEquals("stripTags",stripTags.name());
  assertEquals("Joel\nis a slug",stripTags.apply("<b>Joel</b>\n<button>is<\n/button> a <span>slug</span>",options));
  verify(options);
}
