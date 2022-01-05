@Test public void testDecodeFrame() throws Exception {
  DefaultExtension defaultExtension=new DefaultExtension();
  BinaryFrame binaryFrame=new BinaryFrame();
  binaryFrame.setPayload(ByteBuffer.wrap("test".getBytes()));
  defaultExtension.decodeFrame(binaryFrame);
  assertEquals(ByteBuffer.wrap("test".getBytes()),binaryFrame.getPayloadData());
}
