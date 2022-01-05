@Test() public void testDecode_History_UP() throws IOException {
  AbstractMockChannel channel=getServerSideChannel(url);
  testDecode_assertEquals(channel,UP,Codec2.DecodeResult.NEED_MORE_INPUT,null);
  String request1="aaa\n";
  Object expected1="aaa";
  testDecode_assertEquals(channel,request1,expected1,null);
  testDecode_assertEquals(channel,UP,Codec2.DecodeResult.NEED_MORE_INPUT,expected1);
}
