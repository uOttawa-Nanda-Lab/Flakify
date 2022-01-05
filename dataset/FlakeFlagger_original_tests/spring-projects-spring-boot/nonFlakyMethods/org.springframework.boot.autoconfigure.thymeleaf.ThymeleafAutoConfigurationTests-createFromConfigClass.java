@Test public void createFromConfigClass() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"spring.thymeleaf.mode:XHTML","spring.thymeleaf.suffix:");
  this.context.register(ThymeleafAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  TemplateEngine engine=this.context.getBean(TemplateEngine.class);
  Context attrs=new Context(Locale.UK,Collections.singletonMap("foo","bar"));
  String result=engine.process("template.txt",attrs);
  assertEquals("<html>bar</html>",result);
}
