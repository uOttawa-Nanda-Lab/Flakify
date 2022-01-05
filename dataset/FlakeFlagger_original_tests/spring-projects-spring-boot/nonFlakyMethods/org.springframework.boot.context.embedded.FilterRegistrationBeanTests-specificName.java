@Test public void specificName() throws Exception {
  FilterRegistrationBean bean=new FilterRegistrationBean();
  bean.setName("specificName");
  bean.setFilter(this.filter);
  bean.onStartup(this.servletContext);
  verify(this.servletContext).addFilter("specificName",this.filter);
}
