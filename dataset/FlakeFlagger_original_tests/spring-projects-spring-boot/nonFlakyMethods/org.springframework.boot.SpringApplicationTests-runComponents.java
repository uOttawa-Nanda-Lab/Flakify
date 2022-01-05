@Test public void runComponents() throws Exception {
  this.context=SpringApplication.run(new Object[]{ExampleWebConfig.class,Object.class},new String[0]);
  assertNotNull(this.context);
}
