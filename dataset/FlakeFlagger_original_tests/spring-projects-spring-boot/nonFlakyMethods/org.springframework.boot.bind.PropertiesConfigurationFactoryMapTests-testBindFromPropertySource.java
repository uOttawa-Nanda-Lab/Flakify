@Test public void testBindFromPropertySource() throws Exception {
  this.targetName="foo";
  setupFactory();
  MutablePropertySources sources=new MutablePropertySources();
  sources.addFirst(new MapPropertySource("map",Collections.singletonMap("foo.map.name",(Object)"blah")));
  this.factory.setPropertySources(sources);
  this.factory.afterPropertiesSet();
  Foo foo=this.factory.getObject();
  assertEquals("blah",foo.map.get("name"));
}
