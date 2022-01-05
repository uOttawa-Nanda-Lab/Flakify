@Test public void testAppend8BitBytes() throws WriterException {
  BitArray bits=new BitArray();
  Encoder.append8BitBytes("abc",bits,Encoder.DEFAULT_BYTE_MODE_ENCODING);
  assertEquals(" .XX....X .XX...X. .XX...XX",bits.toString());
  bits=new BitArray();
  Encoder.append8BitBytes("",bits,Encoder.DEFAULT_BYTE_MODE_ENCODING);
  assertEquals("",bits.toString());
}
