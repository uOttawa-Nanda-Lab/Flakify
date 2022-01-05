@Test public void testDefaultMBeanExport(){
  this.context=new AnnotationConfigApplicationContext();
  this.context.register(JmxAutoConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(MBeanExporter.class));
}
