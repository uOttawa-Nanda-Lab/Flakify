@Test public void test_Decode_Check_Payload() throws IOException {
  byte[] header=new byte[]{MAGIC_HIGH,MAGIC_LOW,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
  byte[] request=assemblyDataProtocol(header);
  try {
    testDecode_assertEquals(request,TelnetCodec.DecodeResult.NEED_MORE_INPUT);
    fail();
  }
 catch (  IOException expected) {
    Assert.assertTrue(expected.getMessage().startsWith("Data length too large: " + Bytes.bytes2int(new byte[]{1,1,1,1})));
  }
}
