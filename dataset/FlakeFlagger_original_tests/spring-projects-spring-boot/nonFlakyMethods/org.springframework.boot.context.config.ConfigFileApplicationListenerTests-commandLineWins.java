@Test public void commandLineWins() throws Exception {
  this.environment.getPropertySources().addFirst(new SimpleCommandLinePropertySource("--the.property=fromcommandline"));
  this.initializer.setSearchNames("testproperties");
  this.initializer.onApplicationEvent(this.event);
  String property=this.environment.getProperty("the.property");
  assertThat(property,equalTo("fromcommandline"));
}
