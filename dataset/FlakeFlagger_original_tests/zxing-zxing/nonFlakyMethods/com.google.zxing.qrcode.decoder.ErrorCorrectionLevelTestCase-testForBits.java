@Test public void testForBits(){
  assertSame(ErrorCorrectionLevel.M,ErrorCorrectionLevel.forBits(0));
  assertSame(ErrorCorrectionLevel.L,ErrorCorrectionLevel.forBits(1));
  assertSame(ErrorCorrectionLevel.H,ErrorCorrectionLevel.forBits(2));
  assertSame(ErrorCorrectionLevel.Q,ErrorCorrectionLevel.forBits(3));
  try {
    ErrorCorrectionLevel.forBits(4);
    fail("Should have thrown an exception");
  }
 catch (  IllegalArgumentException iae) {
  }
}
