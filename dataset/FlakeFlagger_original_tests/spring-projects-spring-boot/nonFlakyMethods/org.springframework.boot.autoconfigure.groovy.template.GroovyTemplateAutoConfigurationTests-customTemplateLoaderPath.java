@Test public void customTemplateLoaderPath() throws Exception {
  registerAndRefreshContext("spring.groovy.template.prefix:classpath:/custom-templates/");
  MockHttpServletResponse response=render("custom");
  String result=response.getContentAsString();
  assertThat(result,containsString("custom"));
}
