@Test public void testRandomByteAccess(){
  for (int i=0; i < buffer.capacity(); i++) {
    byte value=(byte)random.nextInt();
    buffer.setByte(i,value);
  }
  random.setSeed(seed);
  for (int i=0; i < buffer.capacity(); i++) {
    byte value=(byte)random.nextInt();
    assertEquals(value,buffer.getByte(i));
  }
}
