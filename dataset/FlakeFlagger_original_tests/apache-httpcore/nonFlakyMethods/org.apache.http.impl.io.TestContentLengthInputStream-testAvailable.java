@Test public void testAvailable() throws IOException {
  InputStream in=new ContentLengthInputStream(new SessionInputBufferMock(new byte[]{1,2,3}),10L);
  Assert.assertEquals(0,in.available());
  in.read();
  Assert.assertEquals(2,in.available());
}
