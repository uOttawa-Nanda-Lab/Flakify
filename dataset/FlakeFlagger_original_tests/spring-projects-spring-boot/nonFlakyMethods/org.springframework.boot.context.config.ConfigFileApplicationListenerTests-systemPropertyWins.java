@Test public void systemPropertyWins() throws Exception {
  System.setProperty("the.property","fromsystem");
  this.initializer.setSearchNames("testproperties");
  this.initializer.onApplicationEvent(this.event);
  String property=this.environment.getProperty("the.property");
  assertThat(property,equalTo("fromsystem"));
}
