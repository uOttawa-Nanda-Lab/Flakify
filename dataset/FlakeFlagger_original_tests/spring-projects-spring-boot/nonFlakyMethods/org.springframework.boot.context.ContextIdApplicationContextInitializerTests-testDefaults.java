@Test public void testDefaults(){
  ConfigurableApplicationContext context=new AnnotationConfigApplicationContext();
  this.initializer.initialize(context);
  assertEquals("application",context.getId());
}
