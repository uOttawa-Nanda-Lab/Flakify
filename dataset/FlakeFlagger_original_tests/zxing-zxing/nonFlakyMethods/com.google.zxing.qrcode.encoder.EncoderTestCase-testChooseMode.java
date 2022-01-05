@Test public void testChooseMode() throws WriterException {
  assertSame(Mode.NUMERIC,Encoder.chooseMode("0"));
  assertSame(Mode.NUMERIC,Encoder.chooseMode("0123456789"));
  assertSame(Mode.ALPHANUMERIC,Encoder.chooseMode("A"));
  assertSame(Mode.ALPHANUMERIC,Encoder.chooseMode("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+-./:"));
  assertSame(Mode.BYTE,Encoder.chooseMode("a"));
  assertSame(Mode.BYTE,Encoder.chooseMode("#"));
  assertSame(Mode.BYTE,Encoder.chooseMode(""));
  assertSame(Mode.BYTE,Encoder.chooseMode(shiftJISString(new byte[]{0x8,0xa,0x8,0xa,0x8,0xa,0x8,(byte)0xa6})));
  assertSame(Mode.BYTE,Encoder.chooseMode(shiftJISString(new byte[]{0x9,0xf,0x9,0x7b})));
  assertSame(Mode.BYTE,Encoder.chooseMode(shiftJISString(new byte[]{0xe,0x4,0x9,0x5,0x9,0x61})));
}
