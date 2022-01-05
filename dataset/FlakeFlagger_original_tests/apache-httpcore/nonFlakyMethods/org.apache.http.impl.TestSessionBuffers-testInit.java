@Test public void testInit() throws Exception {
  ByteArrayOutputStream out=new ByteArrayOutputStream();
  new SessionOutputBufferMock(out);
  try {
    new SessionOutputBufferMock(null,new BasicHttpParams());
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    new SessionOutputBufferMock(out,null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  ByteArrayInputStream in=new ByteArrayInputStream(out.toByteArray());
  new SessionInputBufferMock(in,10);
  try {
    new SessionInputBufferMock(in,-10);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    new SessionOutputBufferMock(out,-10);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    new SessionInputBufferMock((InputStream)null,1024);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    new SessionInputBufferMock(in,10,null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
