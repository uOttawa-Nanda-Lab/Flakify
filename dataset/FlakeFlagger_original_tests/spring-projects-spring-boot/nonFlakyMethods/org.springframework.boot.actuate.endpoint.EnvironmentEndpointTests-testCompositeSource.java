@SuppressWarnings("unchecked") @Test public void testCompositeSource() throws Exception {
  EnvironmentEndpoint report=getEndpointBean();
  CompositePropertySource source=new CompositePropertySource("composite");
  source.addPropertySource(new MapPropertySource("one",Collections.singletonMap("foo",(Object)"bar")));
  source.addPropertySource(new MapPropertySource("two",Collections.singletonMap("foo",(Object)"spam")));
  this.context.getEnvironment().getPropertySources().addFirst(source);
  Map<String,Object> env=report.invoke();
  assertEquals("bar",((Map<String,Object>)env.get("composite:one")).get("foo"));
}
