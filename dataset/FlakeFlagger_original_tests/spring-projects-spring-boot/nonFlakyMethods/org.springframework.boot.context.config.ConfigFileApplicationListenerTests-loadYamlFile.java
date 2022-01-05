@Test public void loadYamlFile() throws Exception {
  this.initializer.setSearchNames("testyaml");
  this.initializer.onApplicationEvent(this.event);
  String property=this.environment.getProperty("my.property");
  assertThat(property,equalTo("fromyamlfile"));
  assertThat(this.environment.getProperty("my.array[0]"),equalTo("1"));
  assertThat(this.environment.getProperty("my.array"),nullValue(String.class));
}
