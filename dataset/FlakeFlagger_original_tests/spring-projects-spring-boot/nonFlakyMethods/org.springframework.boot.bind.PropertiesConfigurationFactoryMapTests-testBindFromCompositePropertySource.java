@Test public void testBindFromCompositePropertySource() throws Exception {
  this.targetName="foo";
  setupFactory();
  MutablePropertySources sources=new MutablePropertySources();
  CompositePropertySource composite=new CompositePropertySource("composite");
  composite.addPropertySource(new MapPropertySource("map",Collections.singletonMap("foo.map.name",(Object)"blah")));
  sources.addFirst(composite);
  this.factory.setPropertySources(sources);
  this.factory.afterPropertiesSet();
  Foo foo=this.factory.getObject();
  assertEquals("blah",foo.map.get("name"));
}
