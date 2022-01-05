@Test public void setExpandsData_FixedCapacity(){
  BitArray.FixedCapacity b=new BitArray.FixedCapacity();
  b.set(63);
  assertEquals(b.data,BigInteger.ZERO.setBit(63).longValue());
}
