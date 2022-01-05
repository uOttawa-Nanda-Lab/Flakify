@Test public void testDefaultBanner() throws Exception {
  SpringApplication application=new SpringApplication(Config.class);
  application.setWebEnvironment(false);
  application.run();
  assertThat(this.out.toString(),containsString(":: Spring Boot ::"));
}
