@Test public void testProcess() throws Exception {
  assertFalse(this.transformer.hasTransformedResource());
  this.transformer.processResource("foo",new ByteArrayInputStream("foo=bar".getBytes()),null);
  assertTrue(this.transformer.hasTransformedResource());
}
