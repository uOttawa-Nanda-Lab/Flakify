@Test public void testChunkingFlag() throws Exception {
  BasicHttpEntity httpentity=new BasicHttpEntity();
  Assert.assertFalse(httpentity.isChunked());
  httpentity.setChunked(true);
  Assert.assertTrue(httpentity.isChunked());
}
