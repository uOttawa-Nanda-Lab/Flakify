@Test public void testContentEncoder() throws Exception {
  ContentEncoderChannel cec=new ContentEncoderChannel(encoder);
  cec.write(bb);
  verify(encoder,times(1)).write(bb);
  assertTrue(cec.isOpen());
  cec.close();
}
