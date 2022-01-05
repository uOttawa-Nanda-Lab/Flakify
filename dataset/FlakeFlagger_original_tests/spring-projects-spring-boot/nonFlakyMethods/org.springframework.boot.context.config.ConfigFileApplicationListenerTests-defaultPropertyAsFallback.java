@Test public void defaultPropertyAsFallback() throws Exception {
  this.event.getEnvironment().getPropertySources().addLast(new MapPropertySource("defaultProperties",Collections.singletonMap("my.fallback",(Object)"foo")));
  this.initializer.onApplicationEvent(this.event);
  String property=this.environment.getProperty("my.fallback");
  assertThat(property,equalTo("foo"));
}
