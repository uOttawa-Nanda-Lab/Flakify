@Test public void testChunkedOutputStreamClose() throws IOException {
  ChunkedOutputStream out=new ChunkedOutputStream(new SessionOutputBufferMock());
  out.close();
  out.close();
  try {
    out.write(new byte[]{1,2,3});
    Assert.fail("IOException should have been thrown");
  }
 catch (  IOException ex) {
  }
  try {
    out.write(1);
    Assert.fail("IOException should have been thrown");
  }
 catch (  IOException ex) {
  }
}
