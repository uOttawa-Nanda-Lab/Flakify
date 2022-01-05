@Test public void useDataDialect() throws Exception {
  this.context.register(ThymeleafAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  TemplateEngine engine=this.context.getBean(TemplateEngine.class);
  Context attrs=new Context(Locale.UK,Collections.singletonMap("foo","bar"));
  String result=engine.process("data-dialect",attrs);
  assertEquals("<html><body data-foo=\"bar\"></body></html>",result);
}
