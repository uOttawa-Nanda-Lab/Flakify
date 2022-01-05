@Test public void testCopyConstructor(){
  PingFrame pingFrame=new PingFrame();
  pingFrame.setPayload(ByteBuffer.allocate(100));
  PongFrame pongFrame=new PongFrame(pingFrame);
  assertEquals("Payload must be equal",pingFrame.getPayloadData(),pongFrame.getPayloadData());
}
