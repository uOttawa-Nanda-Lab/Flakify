@Test public void testDecode_Person_ObjectOnly() throws IOException {
  testDecode_assertEquals(new Person(),Codec2.DecodeResult.NEED_MORE_INPUT);
}
