@Test public void run() throws Exception {
  this.context=SpringApplication.run(ExampleWebConfig.class);
  assertNotNull(this.context);
}
