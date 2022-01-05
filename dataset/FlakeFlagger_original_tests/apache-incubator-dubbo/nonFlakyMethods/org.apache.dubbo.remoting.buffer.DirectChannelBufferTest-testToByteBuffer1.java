@Test public void testToByteBuffer1(){
  byte[] value=new byte[buffer.capacity()];
  random.nextBytes(value);
  buffer.clear();
  buffer.writeBytes(value);
  assertEquals(ByteBuffer.wrap(value),buffer.toByteBuffer());
}
