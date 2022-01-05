@Test public void writeReadByteBufferTest() throws IOException {
  ByteArrayOutputStream os=new ByteArrayOutputStream();
  DataOutputStream dos=new DataOutputStream(os);
  List<ByteBuffer> bufs=new ArrayList<ByteBuffer>();
  bufs.add(null);
  bufs.add(ByteBuffer.allocate(0));
  bufs.add(TestUtils.getIncreasingByteBuffer(99));
  bufs.add(TestUtils.getIncreasingByteBuffer(10,99));
  bufs.add(null);
  for (int k=0; k < bufs.size(); k++) {
    Utils.writeByteBuffer(bufs.get(k),dos);
  }
  ByteBuffer buf=TestUtils.getIncreasingByteBuffer(10,99);
  buf.get();
  Utils.writeByteBuffer(buf,dos);
  DataInputStream dis=new DataInputStream(new ByteArrayInputStream(os.toByteArray()));
  for (int k=0; k < bufs.size(); k++) {
    Assert.assertEquals(bufs.get(k),Utils.readByteBuffer(dis));
  }
  Assert.assertEquals(buf,Utils.readByteBuffer(dis));
  dos.close();
  dis.close();
}
