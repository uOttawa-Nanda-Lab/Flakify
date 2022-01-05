@Test public void createServletMustNotBeNull() throws Exception {
  this.thrown.expect(IllegalArgumentException.class);
  this.thrown.expectMessage("Filter must not be null");
  new FilterRegistrationBean(null);
}
