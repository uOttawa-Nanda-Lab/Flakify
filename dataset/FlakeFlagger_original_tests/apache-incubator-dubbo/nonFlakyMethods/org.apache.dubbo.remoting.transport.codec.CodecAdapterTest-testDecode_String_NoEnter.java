@Test public void testDecode_String_NoEnter() throws IOException {
  testDecode_assertEquals("aaa",Codec2.DecodeResult.NEED_MORE_INPUT);
}
