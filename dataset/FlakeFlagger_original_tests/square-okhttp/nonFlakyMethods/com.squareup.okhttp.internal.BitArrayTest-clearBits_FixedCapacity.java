@Test public void clearBits_FixedCapacity(){
  BitArray.FixedCapacity b=new BitArray.FixedCapacity();
  b.set(1);
  b.set(3);
  b.set(5);
  b.clear();
  assertEquals(b.data,0l);
}
