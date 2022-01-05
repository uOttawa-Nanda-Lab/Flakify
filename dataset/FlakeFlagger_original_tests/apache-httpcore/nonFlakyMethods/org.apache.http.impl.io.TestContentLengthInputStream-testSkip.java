@Test public void testSkip() throws IOException {
  InputStream in=new ContentLengthInputStream(new SessionInputBufferMock(new byte[20]),10L);
  Assert.assertEquals(10,in.skip(10));
  Assert.assertTrue(in.read() == -1);
  in=new ContentLengthInputStream(new SessionInputBufferMock(new byte[20]),10L);
  in.read();
  Assert.assertEquals(9,in.skip(10));
  Assert.assertTrue(in.read() == -1);
  in=new ContentLengthInputStream(new SessionInputBufferMock(new byte[20]),2L);
  in.read();
  in.read();
  Assert.assertTrue(in.skip(10) <= 0);
  Assert.assertTrue(in.skip(-1) == 0);
  Assert.assertTrue(in.read() == -1);
  in=new ContentLengthInputStream(new SessionInputBufferMock(new byte[20]),10L);
  Assert.assertEquals(5,in.skip(5));
  Assert.assertEquals(5,in.read(new byte[20]));
}
