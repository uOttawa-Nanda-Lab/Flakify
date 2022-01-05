@Test public void defaultPropertyAsFallbackDuringFileParsing() throws Exception {
  this.event.getEnvironment().getPropertySources().addLast(new MapPropertySource("defaultProperties",Collections.singletonMap("spring.config.name",(Object)"testproperties")));
  this.initializer.onApplicationEvent(this.event);
  String property=this.environment.getProperty("the.property");
  assertThat(property,equalTo("frompropertiesfile"));
}
