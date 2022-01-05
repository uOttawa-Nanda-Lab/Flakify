@Test public void testXOR2(){
  BitArray v1=new BitArray();
  v1.appendBits(0x2a,7);
  BitArray v2=new BitArray();
  v2.appendBits(0x55,7);
  v1.xor(v2);
  assertEquals(0xfe000000L,getUnsignedInt(v1,0));
}
