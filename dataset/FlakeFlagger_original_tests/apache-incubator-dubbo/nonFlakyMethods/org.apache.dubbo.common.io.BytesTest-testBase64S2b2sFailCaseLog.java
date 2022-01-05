@Test public void testBase64S2b2sFailCaseLog() throws Exception {
  String s1=Bytes.bytes2base64(bytes1);
  byte[] out1=Bytes.base642bytes(s1);
  assertThat(bytes1,is(out1));
  String s2=Bytes.bytes2base64(bytes2);
  byte[] out2=Bytes.base642bytes(s2);
  assertThat(bytes2,is(out2));
}
