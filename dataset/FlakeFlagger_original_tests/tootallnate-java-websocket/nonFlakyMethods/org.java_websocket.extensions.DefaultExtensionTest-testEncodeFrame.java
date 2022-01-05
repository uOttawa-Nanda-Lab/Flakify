@Test public void testEncodeFrame() throws Exception {
  DefaultExtension defaultExtension=new DefaultExtension();
  BinaryFrame binaryFrame=new BinaryFrame();
  binaryFrame.setPayload(ByteBuffer.wrap("test".getBytes()));
  defaultExtension.encodeFrame(binaryFrame);
  assertEquals(ByteBuffer.wrap("test".getBytes()),binaryFrame.getPayloadData());
}
