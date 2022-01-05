@Test public void testDirectByteBufferAllocator(){
  DirectByteBufferAllocator allocator=new DirectByteBufferAllocator();
  ByteBuffer buffer=allocator.allocate(1);
  Assert.assertNotNull(buffer);
  Assert.assertTrue(buffer.isDirect());
  Assert.assertEquals(0,buffer.position());
  Assert.assertEquals(1,buffer.limit());
  Assert.assertEquals(1,buffer.capacity());
  buffer=allocator.allocate(2048);
  Assert.assertTrue(buffer.isDirect());
  Assert.assertEquals(0,buffer.position());
  Assert.assertEquals(2048,buffer.limit());
  Assert.assertEquals(2048,buffer.capacity());
  buffer=allocator.allocate(0);
  Assert.assertTrue(buffer.isDirect());
  Assert.assertEquals(0,buffer.position());
  Assert.assertEquals(0,buffer.limit());
  Assert.assertEquals(0,buffer.capacity());
}
