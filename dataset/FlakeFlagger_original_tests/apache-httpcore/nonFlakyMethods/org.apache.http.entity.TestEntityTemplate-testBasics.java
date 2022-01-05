@Test public void testBasics() throws Exception {
  HttpEntity httpentity=new EntityTemplate(new ContentProducer(){
    public void writeTo(    final OutputStream outstream) throws IOException {
      outstream.write('a');
    }
  }
);
  Assert.assertEquals(-1,httpentity.getContentLength());
  Assert.assertTrue(httpentity.isRepeatable());
  Assert.assertFalse(httpentity.isStreaming());
}
