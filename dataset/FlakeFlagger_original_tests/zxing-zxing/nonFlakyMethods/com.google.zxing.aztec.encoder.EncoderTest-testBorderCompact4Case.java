@Test public void testBorderCompact4Case() throws Exception {
  String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  String alphabet4=alphabet + alphabet + alphabet+ alphabet;
  byte[] data=alphabet4.getBytes(LATIN_1);
  try {
    Encoder.encode(data,0,-4);
    fail("Encode should have failed.  Text can't fit in 1-layer compact");
  }
 catch (  IllegalArgumentException expected) {
  }
  AztecCode aztecCode=Encoder.encode(data,0,Encoder.DEFAULT_AZTEC_LAYERS);
  assertFalse(aztecCode.isCompact());
  assertEquals(4,aztecCode.getLayers());
  aztecCode=Encoder.encode(alphabet4.substring(0,100).getBytes(LATIN_1),10,Encoder.DEFAULT_AZTEC_LAYERS);
  assertTrue(aztecCode.isCompact());
  assertEquals(4,aztecCode.getLayers());
}
