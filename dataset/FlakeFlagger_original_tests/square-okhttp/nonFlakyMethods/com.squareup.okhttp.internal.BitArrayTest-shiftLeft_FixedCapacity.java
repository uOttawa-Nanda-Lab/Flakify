@Test public void shiftLeft_FixedCapacity(){
  BitArray.FixedCapacity b=new BitArray.FixedCapacity();
  b.set(0);
  b.shiftLeft(1);
  assertEquals(b.data,2l);
}
