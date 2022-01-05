@Test public void emptyLines() throws IOException {
  BufferedSource source=newSource("\n\n\n");
  assertEquals("",source.readUtf8Line(true));
  assertEquals("",source.readUtf8Line(true));
  assertEquals("",source.readUtf8Line(true));
  assertTrue(source.exhausted());
}
