@Test public void testLogger() throws Exception {
  ApplicationConfig application=new ApplicationConfig("app");
  application.setLogger("log4j");
  assertThat(application.getLogger(),equalTo("log4j"));
}
