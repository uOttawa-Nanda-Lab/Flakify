@Test public void testDecode_String_MiddleWithEnter() throws IOException {
  testDecode_assertEquals("aaa\r\naaa",Codec2.DecodeResult.NEED_MORE_INPUT);
}
