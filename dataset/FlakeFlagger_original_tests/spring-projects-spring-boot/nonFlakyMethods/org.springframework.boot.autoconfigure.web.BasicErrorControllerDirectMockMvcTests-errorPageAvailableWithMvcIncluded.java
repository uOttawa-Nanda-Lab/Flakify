@Test public void errorPageAvailableWithMvcIncluded() throws Exception {
  setup((ConfigurableWebApplicationContext)new SpringApplication(WebMvcIncludedConfiguration.class).run("--server.port=0"));
  MvcResult response=this.mockMvc.perform(get("/error").accept(MediaType.TEXT_HTML)).andExpect(status().isOk()).andReturn();
  String content=response.getResponse().getContentAsString();
  assertTrue("Wrong content: " + content,content.contains("status=999"));
}
