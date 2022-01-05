@Test public void customizeContainerThroughCallback() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext(CallbackEmbeddedContainerCustomizer.class,BaseConfiguration.class);
  verifyContext();
  assertEquals(9000,getContainerFactory().getPort());
}
