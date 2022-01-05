@Test public void testChunkedOutputStreamSmallChunk() throws IOException {
  ByteArrayOutputStream buffer=new ByteArrayOutputStream();
  ChunkedOutputStream out=new ChunkedOutputStream(new SessionOutputBufferMock(buffer),2);
  out.write('1');
  out.finish();
  out.close();
  byte[] rawdata=buffer.toByteArray();
  Assert.assertEquals(11,rawdata.length);
  Assert.assertEquals('1',rawdata[0]);
  Assert.assertEquals('\r',rawdata[1]);
  Assert.assertEquals('\n',rawdata[2]);
  Assert.assertEquals('1',rawdata[3]);
  Assert.assertEquals('\r',rawdata[4]);
  Assert.assertEquals('\n',rawdata[5]);
  Assert.assertEquals('0',rawdata[6]);
  Assert.assertEquals('\r',rawdata[7]);
  Assert.assertEquals('\n',rawdata[8]);
  Assert.assertEquals('\r',rawdata[9]);
  Assert.assertEquals('\n',rawdata[10]);
}
