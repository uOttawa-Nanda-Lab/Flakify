@Test public void renderTemplate() throws Exception {
  registerAndRefreshContext();
  VelocityConfigurer velocity=this.context.getBean(VelocityConfigurer.class);
  StringWriter writer=new StringWriter();
  Template template=velocity.getVelocityEngine().getTemplate("message.vm");
  template.process();
  VelocityContext velocityContext=new VelocityContext();
  velocityContext.put("greeting","Hello World");
  template.merge(velocityContext,writer);
  assertThat(writer.toString(),containsString("Hello World"));
}
