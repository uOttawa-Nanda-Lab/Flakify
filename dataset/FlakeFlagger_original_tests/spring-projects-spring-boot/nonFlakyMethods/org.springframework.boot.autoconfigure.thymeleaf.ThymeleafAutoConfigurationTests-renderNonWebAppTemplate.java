@Test public void renderNonWebAppTemplate() throws Exception {
  AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(ThymeleafAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  assertEquals(0,context.getBeanNamesForType(ViewResolver.class).length);
  try {
    TemplateEngine engine=context.getBean(TemplateEngine.class);
    Context attrs=new Context(Locale.UK,Collections.singletonMap("greeting","Hello World"));
    String result=engine.process("message",attrs);
    assertThat(result,containsString("Hello World"));
  }
  finally {
    context.close();
  }
}
