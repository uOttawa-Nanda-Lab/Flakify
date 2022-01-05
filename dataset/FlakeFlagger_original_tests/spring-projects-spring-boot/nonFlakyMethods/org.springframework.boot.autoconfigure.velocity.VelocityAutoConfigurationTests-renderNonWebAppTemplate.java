@Test public void renderNonWebAppTemplate() throws Exception {
  AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(VelocityAutoConfiguration.class);
  try {
    VelocityEngine velocity=context.getBean(VelocityEngine.class);
    StringWriter writer=new StringWriter();
    Template template=velocity.getTemplate("message.vm");
    template.process();
    VelocityContext velocityContext=new VelocityContext();
    velocityContext.put("greeting","Hello World");
    template.merge(velocityContext,writer);
    assertThat(writer.toString(),containsString("Hello World"));
  }
  finally {
    context.close();
  }
}
