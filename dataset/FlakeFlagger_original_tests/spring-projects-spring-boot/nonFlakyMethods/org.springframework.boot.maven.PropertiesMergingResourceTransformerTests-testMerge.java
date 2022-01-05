@Test public void testMerge() throws Exception {
  this.transformer.processResource("foo",new ByteArrayInputStream("foo=bar".getBytes()),null);
  this.transformer.processResource("bar",new ByteArrayInputStream("foo=spam".getBytes()),null);
  assertEquals("bar,spam",this.transformer.getData().getProperty("foo"));
}
