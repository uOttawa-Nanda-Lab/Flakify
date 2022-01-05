@Test public void testGetNextSet5(){
  Random r=new SecureRandom(new byte[]{(byte)0xDE,(byte)0xAD,(byte)0xBE,(byte)0xEF});
  for (int i=0; i < 10; i++) {
    BitArray array=new BitArray(1 + r.nextInt(100));
    int numSet=r.nextInt(20);
    for (int j=0; j < numSet; j++) {
      array.set(r.nextInt(array.getSize()));
    }
    int numQueries=r.nextInt(20);
    for (int j=0; j < numQueries; j++) {
      int query=r.nextInt(array.getSize());
      int expected=query;
      while (expected < array.getSize() && !array.get(expected)) {
        expected++;
      }
      int actual=array.getNextSet(query);
      if (actual != expected) {
        array.getNextSet(query);
      }
      assertEquals(expected,actual);
    }
  }
}
