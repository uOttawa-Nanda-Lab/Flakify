@Test public void testForBits(){
  assertSame(Mode.TERMINATOR,Mode.forBits(0x00));
  assertSame(Mode.NUMERIC,Mode.forBits(0x01));
  assertSame(Mode.ALPHANUMERIC,Mode.forBits(0x02));
  assertSame(Mode.BYTE,Mode.forBits(0x04));
  assertSame(Mode.KANJI,Mode.forBits(0x08));
  try {
    Mode.forBits(0x10);
    fail("Should have thrown an exception");
  }
 catch (  IllegalArgumentException iae) {
  }
}
