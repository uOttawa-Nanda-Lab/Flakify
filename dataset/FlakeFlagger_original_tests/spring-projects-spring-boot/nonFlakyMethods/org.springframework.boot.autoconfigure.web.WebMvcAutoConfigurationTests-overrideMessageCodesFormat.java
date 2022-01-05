@Test public void overrideMessageCodesFormat() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.mvc.messageCodesResolverFormat:POSTFIX_ERROR_CODE");
  this.context.register(AllResources.class,Config.class,WebMvcAutoConfiguration.class,HttpMessageConvertersAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(WebMvcAutoConfigurationAdapter.class).getMessageCodesResolver());
}
