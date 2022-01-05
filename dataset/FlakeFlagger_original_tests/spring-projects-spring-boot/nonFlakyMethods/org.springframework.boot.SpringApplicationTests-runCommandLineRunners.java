@Test public void runCommandLineRunners() throws Exception {
  SpringApplication application=new SpringApplication(CommandLineRunConfig.class);
  application.setWebEnvironment(false);
  this.context=application.run("arg");
  assertTrue(this.context.getBean("runnerA",TestCommandLineRunner.class).hasRun());
  assertTrue(this.context.getBean("runnerB",TestCommandLineRunner.class).hasRun());
}
