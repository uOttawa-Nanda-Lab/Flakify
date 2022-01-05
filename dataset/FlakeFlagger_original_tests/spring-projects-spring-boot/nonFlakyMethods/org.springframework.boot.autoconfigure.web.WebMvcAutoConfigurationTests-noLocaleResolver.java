@Test public void noLocaleResolver() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext();
  this.context.register(AllResources.class,Config.class,WebMvcAutoConfiguration.class,HttpMessageConvertersAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  this.thrown.expect(NoSuchBeanDefinitionException.class);
  this.context.getBean(LocaleResolver.class);
}
