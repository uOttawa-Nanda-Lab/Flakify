@Test public void testAppendOneByte() throws Exception {
  ByteArrayBuffer buffer=new ByteArrayBuffer(4);
  Assert.assertEquals(4,buffer.capacity());
  byte[] tmp=new byte[]{1,127,-1,-128,1,-2};
  for (int i=0; i < tmp.length; i++) {
    buffer.append(tmp[i]);
  }
  Assert.assertEquals(8,buffer.capacity());
  Assert.assertEquals(6,buffer.length());
  for (int i=0; i < tmp.length; i++) {
    Assert.assertEquals(tmp[i],buffer.byteAt(i));
  }
}
