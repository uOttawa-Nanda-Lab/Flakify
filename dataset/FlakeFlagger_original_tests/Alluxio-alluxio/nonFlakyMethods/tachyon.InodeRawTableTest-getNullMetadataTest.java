@Test public void getNullMetadataTest() throws TachyonException {
  InodeRawTable inodeRawTable=new InodeRawTable("testTable1",1,0,10,null,System.currentTimeMillis());
  Assert.assertTrue(inodeRawTable.getMetadata().equals(ByteBuffer.allocate(0)));
}
