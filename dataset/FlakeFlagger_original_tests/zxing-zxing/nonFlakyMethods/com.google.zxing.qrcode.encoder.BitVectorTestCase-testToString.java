@Test public void testToString(){
  BitArray v=new BitArray();
  v.appendBits(0xdead,16);
  assertEquals(" XX.XXXX. X.X.XX.X",v.toString());
}
