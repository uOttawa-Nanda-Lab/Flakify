@Test public void testAppendLengthInfo() throws WriterException {
  BitArray bits=new BitArray();
  Encoder.appendLengthInfo(1,Version.getVersionForNumber(1),Mode.NUMERIC,bits);
  assertEquals(" ........ .X",bits.toString());
  bits=new BitArray();
  Encoder.appendLengthInfo(2,Version.getVersionForNumber(10),Mode.ALPHANUMERIC,bits);
  assertEquals(" ........ .X.",bits.toString());
  bits=new BitArray();
  Encoder.appendLengthInfo(255,Version.getVersionForNumber(27),Mode.BYTE,bits);
  assertEquals(" ........ XXXXXXXX",bits.toString());
  bits=new BitArray();
  Encoder.appendLengthInfo(512,Version.getVersionForNumber(40),Mode.KANJI,bits);
  assertEquals(" ..X..... ....",bits.toString());
}
