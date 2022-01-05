@Test public void exit() throws Exception {
  SpringApplication application=new SpringApplication(ExampleConfig.class);
  application.setWebEnvironment(false);
  ApplicationContext context=application.run();
  assertNotNull(context);
  assertEquals(0,SpringApplication.exit(context));
}
