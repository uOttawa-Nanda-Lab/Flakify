@Test public void noErrorIfNotInitialized() throws Exception {
  this.initializer.onApplicationEvent(new ApplicationFailedEvent(new SpringApplication(),new String[0],null,new RuntimeException("Planned")));
  assertThat(this.infoLog.get(0),containsString("Unable to provide auto-configuration report"));
}
