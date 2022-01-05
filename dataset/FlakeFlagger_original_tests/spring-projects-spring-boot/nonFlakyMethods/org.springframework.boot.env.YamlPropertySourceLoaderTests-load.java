@Test public void load() throws Exception {
  ByteArrayResource resource=new ByteArrayResource("foo:\n  bar: spam".getBytes());
  PropertySource<?> source=this.loader.load("resource",resource,null);
  assertNotNull(source);
  assertEquals("spam",source.getProperty("foo.bar"));
}
