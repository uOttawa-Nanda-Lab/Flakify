@Test public void testBasics() throws Exception {
  File tmpfile=File.createTempFile("testfile",".txt");
  tmpfile.deleteOnExit();
  FileEntity httpentity=new FileEntity(tmpfile,ContentType.create("text/plain",HTTP.ISO_8859_1));
  Assert.assertEquals(tmpfile.length(),httpentity.getContentLength());
  final InputStream content=httpentity.getContent();
  Assert.assertNotNull(content);
  content.close();
  Assert.assertTrue(httpentity.isRepeatable());
  Assert.assertFalse(httpentity.isStreaming());
  if (!tmpfile.delete()) {
    Assert.fail("Failed to delete: " + tmpfile);
  }
}
