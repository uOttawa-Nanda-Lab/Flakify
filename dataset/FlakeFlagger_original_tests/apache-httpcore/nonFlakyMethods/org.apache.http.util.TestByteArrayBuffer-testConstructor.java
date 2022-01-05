@Test public void testConstructor() throws Exception {
  ByteArrayBuffer buffer=new ByteArrayBuffer(16);
  Assert.assertEquals(16,buffer.capacity());
  Assert.assertEquals(0,buffer.length());
  Assert.assertNotNull(buffer.buffer());
  Assert.assertEquals(16,buffer.buffer().length);
  try {
    new ByteArrayBuffer(-1);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
