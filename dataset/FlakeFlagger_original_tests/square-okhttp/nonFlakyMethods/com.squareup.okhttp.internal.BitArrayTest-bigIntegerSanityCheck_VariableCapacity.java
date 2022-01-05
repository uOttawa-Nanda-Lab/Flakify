@Test public void bigIntegerSanityCheck_VariableCapacity(){
  BitArray a=new BitArray.VariableCapacity();
  BigInteger b=BigInteger.ZERO;
  a.set(64);
  b=b.setBit(64);
  assertEquals(bigIntegerToString(b),a.toString());
  a.set(1000000);
  b=b.setBit(1000000);
  assertEquals(bigIntegerToString(b),a.toString());
  a.shiftLeft(100);
  b=b.shiftLeft(100);
  assertEquals(bigIntegerToString(b),a.toString());
  a.set(0xF00D);
  b=b.setBit(0xF00D);
  a.set(0xBEEF);
  b=b.setBit(0xBEEF);
  a.set(0xDEAD);
  b=b.setBit(0xDEAD);
  assertEquals(bigIntegerToString(b),a.toString());
  a.shiftLeft(0xB0B);
  b=b.shiftLeft(0xB0B);
  assertEquals(bigIntegerToString(b),a.toString());
  a.toggle(64280);
  b=b.clearBit(64280);
  assertEquals(bigIntegerToString(b),a.toString());
}
