@Test public void testAppendNumericBytes(){
  BitArray bits=new BitArray();
  Encoder.appendNumericBytes("1",bits);
  assertEquals(" ...X",bits.toString());
  bits=new BitArray();
  Encoder.appendNumericBytes("12",bits);
  assertEquals(" ...XX..",bits.toString());
  bits=new BitArray();
  Encoder.appendNumericBytes("123",bits);
  assertEquals(" ...XXXX. XX",bits.toString());
  bits=new BitArray();
  Encoder.appendNumericBytes("1234",bits);
  assertEquals(" ...XXXX. XX.X..",bits.toString());
  bits=new BitArray();
  Encoder.appendNumericBytes("",bits);
  assertEquals("",bits.toString());
}
