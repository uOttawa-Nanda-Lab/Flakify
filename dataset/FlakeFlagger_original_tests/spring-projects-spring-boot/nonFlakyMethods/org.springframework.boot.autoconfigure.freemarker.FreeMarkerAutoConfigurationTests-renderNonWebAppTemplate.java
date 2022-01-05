@Test public void renderNonWebAppTemplate() throws Exception {
  AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(FreeMarkerAutoConfiguration.class);
  try {
    freemarker.template.Configuration freemarker=context.getBean(freemarker.template.Configuration.class);
    StringWriter writer=new StringWriter();
    freemarker.getTemplate("message.ftl").process(this,writer);
    assertThat(writer.toString(),containsString("Hello World"));
  }
  finally {
    context.close();
  }
}
