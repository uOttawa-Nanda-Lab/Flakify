@Test public void testArchitecture() throws Exception {
  ApplicationConfig application=new ApplicationConfig("app");
  application.setArchitecture("arch");
  assertThat(application.getArchitecture(),equalTo("arch"));
}
