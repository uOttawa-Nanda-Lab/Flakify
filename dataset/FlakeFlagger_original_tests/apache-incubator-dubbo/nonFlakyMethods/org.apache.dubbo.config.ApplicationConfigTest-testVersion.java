@Test public void testVersion() throws Exception {
  ApplicationConfig application=new ApplicationConfig("app");
  application.setVersion("1.0.0");
  assertThat(application.getVersion(),equalTo("1.0.0"));
  Map<String,String> parameters=new HashMap<String,String>();
  ApplicationConfig.appendParameters(parameters,application);
  assertThat(parameters,hasEntry("application.version","1.0.0"));
}
