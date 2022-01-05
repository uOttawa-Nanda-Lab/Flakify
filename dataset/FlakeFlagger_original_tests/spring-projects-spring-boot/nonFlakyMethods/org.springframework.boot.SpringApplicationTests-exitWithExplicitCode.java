@Test public void exitWithExplicitCode() throws Exception {
  SpringApplication application=new SpringApplication(ExampleConfig.class);
  application.setWebEnvironment(false);
  ApplicationContext context=application.run();
  assertNotNull(context);
  assertEquals(2,SpringApplication.exit(context,new ExitCodeGenerator(){
    @Override public int getExitCode(){
      return 2;
    }
  }
));
}
