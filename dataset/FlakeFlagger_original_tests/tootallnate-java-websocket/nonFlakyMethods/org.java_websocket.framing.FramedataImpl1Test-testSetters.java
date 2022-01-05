@Test public void testSetters(){
  FramedataImpl1 frame=FramedataImpl1.get(Opcode.BINARY);
  frame.setFin(false);
  assertEquals("Fin must not be set",false,frame.isFin());
  frame.setTransferemasked(true);
  assertEquals("TransferedMask must be set",true,frame.getTransfereMasked());
  ByteBuffer buffer=ByteBuffer.allocate(100);
  frame.setPayload(buffer);
  assertEquals("Payload must be of size 100",100,frame.getPayloadData().capacity());
  frame.setRSV1(true);
  assertEquals("RSV1 must be true",true,frame.isRSV1());
  frame.setRSV2(true);
  assertEquals("RSV2 must be true",true,frame.isRSV2());
  frame.setRSV3(true);
  assertEquals("RSV3 must be true",true,frame.isRSV3());
}
