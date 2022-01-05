@Test public void runAndWait() throws Exception {
  ConfigurableApplicationContext context=SpringApplication.run(ClientConfiguration.class,"--spring.main.web_environment=false");
  long count=context.getBean(ClientConfiguration.class).latch.getCount();
  context.close();
  assertEquals(0,count);
}
