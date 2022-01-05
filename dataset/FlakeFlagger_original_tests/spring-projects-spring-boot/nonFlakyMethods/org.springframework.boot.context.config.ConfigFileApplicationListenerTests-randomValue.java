@Test public void randomValue() throws Exception {
  this.initializer.onApplicationEvent(this.event);
  String property=this.environment.getProperty("random.value");
  assertThat(property,notNullValue());
}
