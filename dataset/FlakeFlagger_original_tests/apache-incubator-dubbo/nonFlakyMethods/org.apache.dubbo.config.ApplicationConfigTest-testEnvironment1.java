@Test public void testEnvironment1() throws Exception {
  ApplicationConfig application=new ApplicationConfig("app");
  application.setEnvironment("develop");
  assertThat(application.getEnvironment(),equalTo("develop"));
  application.setEnvironment("test");
  assertThat(application.getEnvironment(),equalTo("test"));
  application.setEnvironment("product");
  assertThat(application.getEnvironment(),equalTo("product"));
}
