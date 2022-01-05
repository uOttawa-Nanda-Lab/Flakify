@Test public void testAppend(){
  FramedataImpl1 frame0=FramedataImpl1.get(Opcode.BINARY);
  frame0.setFin(false);
  frame0.setPayload(ByteBuffer.wrap("first".getBytes()));
  FramedataImpl1 frame1=FramedataImpl1.get(Opcode.BINARY);
  frame1.setPayload(ByteBuffer.wrap("second".getBytes()));
  frame0.append(frame1);
  assertEquals("Fin must be set",true,frame0.isFin());
  assertArrayEquals("Payload must be equal","firstsecond".getBytes(),frame0.getPayloadData().array());
}
