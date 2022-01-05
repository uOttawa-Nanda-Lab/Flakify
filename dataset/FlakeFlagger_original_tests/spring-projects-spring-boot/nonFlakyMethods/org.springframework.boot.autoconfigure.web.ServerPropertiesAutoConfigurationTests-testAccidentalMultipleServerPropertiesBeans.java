@Test public void testAccidentalMultipleServerPropertiesBeans() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext();
  this.context.register(Config.class,MutiServerPropertiesBeanConfig.class,ServerPropertiesAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.thrown.expect(ApplicationContextException.class);
  this.thrown.expectMessage("Multiple ServerProperties");
  this.context.refresh();
}
