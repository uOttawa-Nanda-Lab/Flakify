@Test public void testQosPort() throws Exception {
  ApplicationConfig application=new ApplicationConfig("app");
  application.setQosPort(8080);
  assertThat(application.getQosPort(),equalTo(8080));
}
