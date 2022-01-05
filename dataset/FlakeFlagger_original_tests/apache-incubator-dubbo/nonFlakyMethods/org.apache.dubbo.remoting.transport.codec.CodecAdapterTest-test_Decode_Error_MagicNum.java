@Test public void test_Decode_Error_MagicNum() throws IOException {
  HashMap<byte[],Object> inputBytes=new HashMap<byte[],Object>();
  inputBytes.put(new byte[]{0},TelnetCodec.DecodeResult.NEED_MORE_INPUT);
  inputBytes.put(new byte[]{MAGIC_HIGH,0},TelnetCodec.DecodeResult.NEED_MORE_INPUT);
  inputBytes.put(new byte[]{0,MAGIC_LOW},TelnetCodec.DecodeResult.NEED_MORE_INPUT);
  for (  byte[] input : inputBytes.keySet()) {
    testDecode_assertEquals(assemblyDataProtocol(input),inputBytes.get(input));
  }
}
