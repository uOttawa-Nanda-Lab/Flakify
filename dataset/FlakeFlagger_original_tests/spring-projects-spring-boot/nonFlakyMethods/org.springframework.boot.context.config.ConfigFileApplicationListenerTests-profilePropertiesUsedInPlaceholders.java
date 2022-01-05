@Test public void profilePropertiesUsedInPlaceholders() throws Exception {
  this.initializer.setSearchNames("enableprofile");
  this.initializer.onApplicationEvent(this.event);
  String property=this.environment.getProperty("one.more");
  assertThat(property,equalTo("fromprofilepropertiesfile"));
}
