@Test public void renderTemplate() throws Exception {
  registerAndRefreshContext();
  FreeMarkerConfigurer freemarker=this.context.getBean(FreeMarkerConfigurer.class);
  StringWriter writer=new StringWriter();
  freemarker.getConfiguration().getTemplate("message.ftl").process(this,writer);
  assertThat(writer.toString(),containsString("Hello World"));
}
