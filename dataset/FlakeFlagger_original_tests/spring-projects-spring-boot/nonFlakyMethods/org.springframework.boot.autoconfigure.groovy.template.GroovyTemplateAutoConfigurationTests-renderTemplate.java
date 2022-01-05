@Test public void renderTemplate() throws Exception {
  registerAndRefreshContext();
  TemplateEngine engine=this.context.getBean(TemplateEngine.class);
  Writer writer=new StringWriter();
  engine.createTemplate(new ClassPathResource("templates/message.tpl").getFile()).make(new HashMap<String,Object>(Collections.singletonMap("greeting","Hello World"))).writeTo(writer);
  assertThat(writer.toString(),containsString("Hello World"));
}
