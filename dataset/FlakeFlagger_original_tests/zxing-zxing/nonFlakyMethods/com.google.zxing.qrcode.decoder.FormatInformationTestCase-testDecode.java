@Test public void testDecode(){
  FormatInformation expected=FormatInformation.decodeFormatInformation(MASKED_TEST_FORMAT_INFO,MASKED_TEST_FORMAT_INFO);
  assertNotNull(expected);
  assertEquals((byte)0x07,expected.getDataMask());
  assertSame(ErrorCorrectionLevel.Q,expected.getErrorCorrectionLevel());
  assertEquals(expected,FormatInformation.decodeFormatInformation(UNMASKED_TEST_FORMAT_INFO,MASKED_TEST_FORMAT_INFO));
}
