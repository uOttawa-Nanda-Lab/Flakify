@Test public void multipleShifts_FixedCapacity(){
  BitArray.FixedCapacity b=new BitArray.FixedCapacity();
  b.set(10);
  b.shiftLeft(2);
  b.shiftLeft(2);
  assertEquals(b.data,BigInteger.ZERO.setBit(10).shiftLeft(2).shiftLeft(2).longValue());
}
