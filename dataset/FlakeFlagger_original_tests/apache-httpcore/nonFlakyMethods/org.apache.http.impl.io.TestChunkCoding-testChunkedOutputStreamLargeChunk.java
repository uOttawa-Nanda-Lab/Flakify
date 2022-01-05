@Test public void testChunkedOutputStreamLargeChunk() throws IOException {
  SessionOutputBufferMock buffer=new SessionOutputBufferMock();
  ChunkedOutputStream out=new ChunkedOutputStream(buffer,2);
  out.write(new byte[]{'1','2','3','4'});
  out.finish();
  out.close();
  byte[] rawdata=buffer.getData();
  Assert.assertEquals(14,rawdata.length);
  Assert.assertEquals('4',rawdata[0]);
  Assert.assertEquals('\r',rawdata[1]);
  Assert.assertEquals('\n',rawdata[2]);
  Assert.assertEquals('1',rawdata[3]);
  Assert.assertEquals('2',rawdata[4]);
  Assert.assertEquals('3',rawdata[5]);
  Assert.assertEquals('4',rawdata[6]);
  Assert.assertEquals('\r',rawdata[7]);
  Assert.assertEquals('\n',rawdata[8]);
  Assert.assertEquals('0',rawdata[9]);
  Assert.assertEquals('\r',rawdata[10]);
  Assert.assertEquals('\n',rawdata[11]);
  Assert.assertEquals('\r',rawdata[12]);
  Assert.assertEquals('\n',rawdata[13]);
}
