@Test public void prefixed() throws Exception {
  this.resolver=new RelaxedPropertyResolver(this.environment,"a.b.c.");
  this.source.put("a.b.c.d","test");
  assertThat(this.resolver.containsProperty("d"),equalTo(true));
  assertThat(this.resolver.getProperty("d"),equalTo("test"));
}
