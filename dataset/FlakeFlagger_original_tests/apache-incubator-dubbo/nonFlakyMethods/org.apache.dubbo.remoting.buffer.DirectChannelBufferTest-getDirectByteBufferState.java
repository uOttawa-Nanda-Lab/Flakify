@Test public void getDirectByteBufferState(){
  ByteBuffer dst=ByteBuffer.allocateDirect(4);
  dst.position(1);
  dst.limit(3);
  buffer.setByte(0,(byte)1);
  buffer.setByte(1,(byte)2);
  buffer.setByte(2,(byte)3);
  buffer.setByte(3,(byte)4);
  buffer.getBytes(1,dst);
  assertEquals(3,dst.position());
  assertEquals(3,dst.limit());
  dst.clear();
  assertEquals(0,dst.get(0));
  assertEquals(2,dst.get(1));
  assertEquals(3,dst.get(2));
  assertEquals(0,dst.get(3));
}
