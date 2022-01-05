@Test public void overrideCharacterEncoding() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"spring.thymeleaf.encoding:UTF-16");
  this.context.register(ThymeleafAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  this.context.getBean(TemplateEngine.class).initialize();
  ITemplateResolver resolver=this.context.getBean(ITemplateResolver.class);
  assertTrue(resolver instanceof TemplateResolver);
  assertEquals("UTF-16",((TemplateResolver)resolver).getCharacterEncoding());
  ThymeleafViewResolver views=this.context.getBean(ThymeleafViewResolver.class);
  assertEquals("UTF-16",views.getCharacterEncoding());
  assertEquals("text/html;charset=UTF-16",views.getContentType());
}
