@Test public void loadTwoPropertiesFiles() throws Exception {
  this.initializer.setSearchNames("moreproperties,testproperties");
  this.initializer.onApplicationEvent(this.event);
  String property=this.environment.getProperty("the.property");
  assertThat(property,equalTo("frompropertiesfile"));
}
