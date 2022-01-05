@Test public void addUrlPatternMustNotBeNull() throws Exception {
  FilterRegistrationBean bean=new FilterRegistrationBean(this.filter);
  this.thrown.expect(IllegalArgumentException.class);
  this.thrown.expectMessage("UrlPatterns must not be null");
  bean.addUrlPatterns((String[])null);
}
