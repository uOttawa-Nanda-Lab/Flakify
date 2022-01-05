@Test public void testConstructor(){
  PingFrame frame=new PingFrame();
  assertEquals("Opcode must be equal",Opcode.PING,frame.getOpcode());
  assertEquals("Fin must be set",true,frame.isFin());
  assertEquals("TransferedMask must not be set",false,frame.getTransfereMasked());
  assertEquals("Payload must be empty",0,frame.getPayloadData().capacity());
  assertEquals("RSV1 must be false",false,frame.isRSV1());
  assertEquals("RSV2 must be false",false,frame.isRSV2());
  assertEquals("RSV3 must be false",false,frame.isRSV3());
  try {
    frame.isValid();
  }
 catch (  InvalidDataException e) {
    fail("InvalidDataException should not be thrown");
  }
}
