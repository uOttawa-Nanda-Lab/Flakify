@Test public void createMappingMustNotBeNull() throws Exception {
  this.thrown.expect(IllegalArgumentException.class);
  this.thrown.expectMessage("UrlMappings must not be null");
  new ServletRegistrationBean(this.servlet,(String[])null);
}
