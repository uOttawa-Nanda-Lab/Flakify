@Test public void testgetContent() throws Exception {
  HttpEntity httpentity=new EntityTemplate(new ContentProducer(){
    public void writeTo(    final OutputStream outstream) throws IOException {
      outstream.write('a');
    }
  }
);
  try {
    httpentity.getContent();
    Assert.fail("UnsupportedOperationException should have been thrown");
  }
 catch (  UnsupportedOperationException ex) {
  }
}
