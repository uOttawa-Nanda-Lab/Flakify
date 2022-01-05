@Test public void toggleBit_FixedCapacity(){
  BitArray.FixedCapacity b=new BitArray.FixedCapacity();
  b.set(63);
  b.toggle(63);
  assertEquals(b.data,0l);
  b.toggle(1);
  assertEquals(b.data,2l);
}
