@Test public void errorPageNotAvailableWithWhitelabelDisabled() throws Exception {
  setup((ConfigurableWebApplicationContext)new SpringApplication(WebMvcIncludedConfiguration.class).run("--server.port=0","--error.whitelabel.enabled=false"));
  thrown.expect(ServletException.class);
  this.mockMvc.perform(get("/error").accept(MediaType.TEXT_HTML));
}
