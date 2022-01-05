@Test public void testAppendBytes() throws WriterException {
  BitArray bits=new BitArray();
  Encoder.appendBytes("1",Mode.NUMERIC,bits,Encoder.DEFAULT_BYTE_MODE_ENCODING);
  assertEquals(" ...X",bits.toString());
  bits=new BitArray();
  Encoder.appendBytes("A",Mode.ALPHANUMERIC,bits,Encoder.DEFAULT_BYTE_MODE_ENCODING);
  assertEquals(" ..X.X.",bits.toString());
  try {
    Encoder.appendBytes("a",Mode.ALPHANUMERIC,bits,Encoder.DEFAULT_BYTE_MODE_ENCODING);
  }
 catch (  WriterException we) {
  }
  bits=new BitArray();
  Encoder.appendBytes("abc",Mode.BYTE,bits,Encoder.DEFAULT_BYTE_MODE_ENCODING);
  assertEquals(" .XX....X .XX...X. .XX...XX",bits.toString());
  Encoder.appendBytes("\0",Mode.BYTE,bits,Encoder.DEFAULT_BYTE_MODE_ENCODING);
  bits=new BitArray();
  Encoder.appendBytes(shiftJISString(new byte[]{(byte)0x93,0x5f}),Mode.KANJI,bits,Encoder.DEFAULT_BYTE_MODE_ENCODING);
  assertEquals(" .XX.XX.. XXXXX",bits.toString());
}
