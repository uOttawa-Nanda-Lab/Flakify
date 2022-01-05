@Test public void prefixedRelaxed() throws Exception {
  this.resolver=new RelaxedPropertyResolver(this.environment,"a.");
  this.source.put("A_B","test");
  this.source.put("a.foobar","spam");
  assertThat(this.resolver.containsProperty("b"),equalTo(true));
  assertThat(this.resolver.getProperty("b"),equalTo("test"));
  assertThat(this.resolver.getProperty("foo-bar"),equalTo("spam"));
}
