@Test public void commandLineArgsApplyToSpringApplication() throws Exception {
  TestSpringApplication application=new TestSpringApplication(ExampleConfig.class);
  application.setWebEnvironment(false);
  application.run("--spring.main.show_banner=false");
  assertThat(application.getShowBanner(),is(false));
}
