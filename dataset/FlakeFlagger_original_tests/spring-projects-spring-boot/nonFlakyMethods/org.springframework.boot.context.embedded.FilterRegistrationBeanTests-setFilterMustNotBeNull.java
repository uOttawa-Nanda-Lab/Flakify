@Test public void setFilterMustNotBeNull() throws Exception {
  FilterRegistrationBean bean=new FilterRegistrationBean();
  this.thrown.expect(IllegalArgumentException.class);
  this.thrown.expectMessage("Filter must not be null");
  bean.onStartup(this.servletContext);
}
