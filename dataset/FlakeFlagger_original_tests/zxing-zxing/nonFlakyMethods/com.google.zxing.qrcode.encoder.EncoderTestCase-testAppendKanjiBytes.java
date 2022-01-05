@Test public void testAppendKanjiBytes() throws WriterException {
  BitArray bits=new BitArray();
  Encoder.appendKanjiBytes(shiftJISString(new byte[]{(byte)0x93,0x5f}),bits);
  assertEquals(" .XX.XX.. XXXXX",bits.toString());
  Encoder.appendKanjiBytes(shiftJISString(new byte[]{(byte)0xe4,(byte)0xaa}),bits);
  assertEquals(" .XX.XX.. XXXXXXX. X.X.X.X. X.",bits.toString());
}
