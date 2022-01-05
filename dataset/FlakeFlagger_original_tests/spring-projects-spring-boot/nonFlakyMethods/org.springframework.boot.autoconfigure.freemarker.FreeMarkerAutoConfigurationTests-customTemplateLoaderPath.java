@Test public void customTemplateLoaderPath() throws Exception {
  registerAndRefreshContext("spring.freemarker.templateLoaderPath:classpath:/custom-templates/");
  MockHttpServletResponse response=render("custom");
  String result=response.getContentAsString();
  assertThat(result,containsString("custom"));
}
