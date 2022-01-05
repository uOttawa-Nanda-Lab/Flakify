@Test public void customTemplateLoaderPath() throws Exception {
  registerAndRefreshContext("spring.velocity.resourceLoaderPath:classpath:/custom-templates/");
  MockHttpServletResponse response=render("custom");
  String result=response.getContentAsString();
  assertThat(result,containsString("custom"));
}
