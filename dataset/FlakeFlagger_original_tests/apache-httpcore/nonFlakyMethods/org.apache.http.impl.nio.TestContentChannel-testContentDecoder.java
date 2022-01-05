@Test public void testContentDecoder() throws Exception {
  ContentDecoderChannel cdc=new ContentDecoderChannel(decoder);
  cdc.read(bb);
  verify(decoder,times(1)).read(bb);
  assertTrue(cdc.isOpen());
  cdc.close();
}
