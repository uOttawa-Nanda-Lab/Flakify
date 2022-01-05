@Test public void testNumBytes(){
  BitArray v=new BitArray();
  assertEquals(0,v.getSizeInBytes());
  v.appendBit(false);
  assertEquals(1,v.getSizeInBytes());
  v.appendBits(0,7);
  assertEquals(1,v.getSizeInBytes());
  v.appendBits(0,8);
  assertEquals(2,v.getSizeInBytes());
  v.appendBits(0,1);
  assertEquals(3,v.getSizeInBytes());
}
