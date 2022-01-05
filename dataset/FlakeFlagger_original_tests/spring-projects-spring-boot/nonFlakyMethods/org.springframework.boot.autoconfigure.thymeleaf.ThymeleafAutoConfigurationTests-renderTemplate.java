@Test public void renderTemplate() throws Exception {
  this.context.register(ThymeleafAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  TemplateEngine engine=this.context.getBean(TemplateEngine.class);
  Context attrs=new Context(Locale.UK,Collections.singletonMap("foo","bar"));
  String result=engine.process("home",attrs);
  assertEquals("<html><body>bar</body></html>",result);
}
