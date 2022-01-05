@Test public void bufferedReaderCompatibleWithTrailingNewline() throws IOException {
  BufferedSource source=newSource("abc\ndef\n");
  assertEquals("abc",source.readUtf8Line(false));
  assertEquals("def",source.readUtf8Line(false));
  assertEquals(null,source.readUtf8Line(false));
}
