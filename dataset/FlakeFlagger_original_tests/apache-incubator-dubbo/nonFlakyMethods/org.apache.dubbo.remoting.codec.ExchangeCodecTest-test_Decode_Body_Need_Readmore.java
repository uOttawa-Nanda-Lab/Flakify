@Test public void test_Decode_Body_Need_Readmore() throws IOException {
  byte[] header=new byte[]{MAGIC_HIGH,MAGIC_LOW,0,0,0,0,0,0,0,0,0,0,0,0,1,1,'a','a'};
  testDecode_assertEquals(header,TelnetCodec.DecodeResult.NEED_MORE_INPUT);
}
