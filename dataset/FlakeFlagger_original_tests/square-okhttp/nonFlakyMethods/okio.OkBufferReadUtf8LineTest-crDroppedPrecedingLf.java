@Test public void crDroppedPrecedingLf() throws IOException {
  BufferedSource source=newSource("abc\r\ndef\r\nghi\rjkl\r\n");
  assertEquals("abc",source.readUtf8Line(true));
  assertEquals("def",source.readUtf8Line(true));
  assertEquals("ghi\rjkl",source.readUtf8Line(true));
}
