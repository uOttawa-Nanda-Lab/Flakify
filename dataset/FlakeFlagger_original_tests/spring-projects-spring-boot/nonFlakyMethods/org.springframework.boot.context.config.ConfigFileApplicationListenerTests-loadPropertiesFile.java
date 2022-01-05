@Test public void loadPropertiesFile() throws Exception {
  this.initializer.setSearchNames("testproperties");
  this.initializer.onApplicationEvent(this.event);
  String property=this.environment.getProperty("the.property");
  assertThat(property,equalTo("frompropertiesfile"));
}
