@Test public void testCustomBanner() throws Exception {
  SpringApplication application=new SpringApplication(Config.class);
  application.setWebEnvironment(false);
  application.setBanner(new DummyBanner());
  application.run();
  assertThat(this.out.toString(),containsString("My Banner"));
}
