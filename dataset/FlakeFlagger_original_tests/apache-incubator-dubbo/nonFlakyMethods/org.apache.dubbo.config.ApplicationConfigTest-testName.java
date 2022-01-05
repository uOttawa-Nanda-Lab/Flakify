@Test public void testName() throws Exception {
  ApplicationConfig application=new ApplicationConfig();
  application.setName("app");
  assertThat(application.getName(),equalTo("app"));
  application=new ApplicationConfig("app2");
  assertThat(application.getName(),equalTo("app2"));
  Map<String,String> parameters=new HashMap<String,String>();
  ApplicationConfig.appendParameters(parameters,application);
  assertThat(parameters,hasEntry(Constants.APPLICATION_KEY,"app2"));
}
