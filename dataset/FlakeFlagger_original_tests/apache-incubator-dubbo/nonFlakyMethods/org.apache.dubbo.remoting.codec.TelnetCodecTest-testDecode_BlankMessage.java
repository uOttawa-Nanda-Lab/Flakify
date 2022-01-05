@Test public void testDecode_BlankMessage() throws IOException {
  testDecode_assertEquals(new byte[]{},Codec2.DecodeResult.NEED_MORE_INPUT);
}
