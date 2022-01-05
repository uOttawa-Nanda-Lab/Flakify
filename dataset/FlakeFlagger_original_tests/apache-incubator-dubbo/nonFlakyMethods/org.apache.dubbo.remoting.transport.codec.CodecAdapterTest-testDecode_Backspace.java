@Test public void testDecode_Backspace() throws IOException {
  testDecode_assertEquals(new byte[]{'\b'},Codec2.DecodeResult.NEED_MORE_INPUT,new String(new byte[]{32,8}));
  byte[] chineseBytes="中".getBytes();
  byte[] request=join(chineseBytes,new byte[]{'\b'});
  testDecode_assertEquals(request,Codec2.DecodeResult.NEED_MORE_INPUT,new String(new byte[]{32,32,8,8}));
  testDecode_assertEquals(new byte[]{'a','x',-1,'x','\b'},Codec2.DecodeResult.NEED_MORE_INPUT,new String(new byte[]{32,32,8,8}));
}
