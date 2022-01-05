@Test public void noInitializers() throws Exception {
  this.listener.onApplicationEvent(new ApplicationEnvironmentPreparedEvent(new SpringApplication(),new String[0],this.context.getEnvironment()));
}
