@Test public void testOutput() throws Exception {
  this.transformer.setResource("foo");
  this.transformer.processResource("foo",new ByteArrayInputStream("foo=bar".getBytes()),null);
  ByteArrayOutputStream out=new ByteArrayOutputStream();
  JarOutputStream os=new JarOutputStream(out);
  this.transformer.modifyOutputStream(os);
  os.flush();
  os.close();
  assertNotNull(out.toByteArray());
  assertTrue(out.toByteArray().length > 0);
}
