@Test public void testSequentialByteAccess(){
  buffer.writerIndex(0);
  for (int i=0; i < buffer.capacity(); i++) {
    byte value=(byte)random.nextInt();
    assertEquals(i,buffer.writerIndex());
    assertTrue(buffer.writable());
    buffer.writeByte(value);
  }
  assertEquals(0,buffer.readerIndex());
  assertEquals(buffer.capacity(),buffer.writerIndex());
  assertFalse(buffer.writable());
  random.setSeed(seed);
  for (int i=0; i < buffer.capacity(); i++) {
    byte value=(byte)random.nextInt();
    assertEquals(i,buffer.readerIndex());
    assertTrue(buffer.readable());
    assertEquals(value,buffer.readByte());
  }
  assertEquals(buffer.capacity(),buffer.readerIndex());
  assertEquals(buffer.capacity(),buffer.writerIndex());
  assertFalse(buffer.readable());
  assertFalse(buffer.writable());
}
