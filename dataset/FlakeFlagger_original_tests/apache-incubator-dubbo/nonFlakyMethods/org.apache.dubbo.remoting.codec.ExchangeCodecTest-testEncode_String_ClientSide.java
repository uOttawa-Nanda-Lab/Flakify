@Test public void testEncode_String_ClientSide() throws IOException {
  testEecode_assertEquals("aaa","aaa\r\n".getBytes(),false);
}
