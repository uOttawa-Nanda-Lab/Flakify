@Test public void testAppendAlphanumericBytes() throws WriterException {
  BitArray bits=new BitArray();
  Encoder.appendAlphanumericBytes("A",bits);
  assertEquals(" ..X.X.",bits.toString());
  bits=new BitArray();
  Encoder.appendAlphanumericBytes("AB",bits);
  assertEquals(" ..XXX..X X.X",bits.toString());
  bits=new BitArray();
  Encoder.appendAlphanumericBytes("ABC",bits);
  assertEquals(" ..XXX..X X.X..XX. .",bits.toString());
  bits=new BitArray();
  Encoder.appendAlphanumericBytes("",bits);
  assertEquals("",bits.toString());
  try {
    Encoder.appendAlphanumericBytes("abc",new BitArray());
  }
 catch (  WriterException we) {
  }
}
