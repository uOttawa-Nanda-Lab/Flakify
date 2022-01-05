@Test public void testUserSpecifiedLayers() throws Exception {
  byte[] alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ".getBytes(LATIN_1);
  AztecCode aztec=Encoder.encode(alphabet,25,-2);
  assertEquals(2,aztec.getLayers());
  assertTrue(aztec.isCompact());
  aztec=Encoder.encode(alphabet,25,32);
  assertEquals(32,aztec.getLayers());
  assertFalse(aztec.isCompact());
  try {
    Encoder.encode(alphabet,25,33);
    fail("Encode should have failed.  No such thing as 33 layers");
  }
 catch (  IllegalArgumentException expected) {
  }
  try {
    Encoder.encode(alphabet,25,-1);
    fail("Encode should have failed.  Text can't fit in 1-layer compact");
  }
 catch (  IllegalArgumentException expected) {
  }
}
