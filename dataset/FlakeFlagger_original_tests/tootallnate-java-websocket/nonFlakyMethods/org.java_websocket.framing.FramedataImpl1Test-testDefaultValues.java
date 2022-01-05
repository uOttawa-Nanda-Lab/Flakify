@Test public void testDefaultValues(){
  FramedataImpl1 binary=FramedataImpl1.get(Opcode.BINARY);
  assertEquals("Opcode must be equal",Opcode.BINARY,binary.getOpcode());
  assertEquals("Fin must be set",true,binary.isFin());
  assertEquals("TransferedMask must not be set",false,binary.getTransfereMasked());
  assertEquals("Payload must be empty",0,binary.getPayloadData().capacity());
  assertEquals("RSV1 must be false",false,binary.isRSV1());
  assertEquals("RSV2 must be false",false,binary.isRSV2());
  assertEquals("RSV3 must be false",false,binary.isRSV3());
}
