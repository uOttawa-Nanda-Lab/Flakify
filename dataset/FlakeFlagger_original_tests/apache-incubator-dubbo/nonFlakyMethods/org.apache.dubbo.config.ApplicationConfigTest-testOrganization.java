@Test public void testOrganization() throws Exception {
  ApplicationConfig application=new ApplicationConfig("app");
  application.setOrganization("org");
  assertThat(application.getOrganization(),equalTo("org"));
}
