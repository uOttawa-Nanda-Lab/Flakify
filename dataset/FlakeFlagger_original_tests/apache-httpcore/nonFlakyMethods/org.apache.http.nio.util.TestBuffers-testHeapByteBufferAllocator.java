@Test public void testHeapByteBufferAllocator(){
  HeapByteBufferAllocator allocator=new HeapByteBufferAllocator();
  ByteBuffer buffer=allocator.allocate(1);
  Assert.assertNotNull(buffer);
  Assert.assertFalse(buffer.isDirect());
  Assert.assertEquals(0,buffer.position());
  Assert.assertEquals(1,buffer.limit());
  Assert.assertEquals(1,buffer.capacity());
  buffer=allocator.allocate(2048);
  Assert.assertFalse(buffer.isDirect());
  Assert.assertEquals(0,buffer.position());
  Assert.assertEquals(2048,buffer.limit());
  Assert.assertEquals(2048,buffer.capacity());
  buffer=allocator.allocate(0);
  Assert.assertFalse(buffer.isDirect());
  Assert.assertEquals(0,buffer.position());
  Assert.assertEquals(0,buffer.limit());
  Assert.assertEquals(0,buffer.capacity());
}
