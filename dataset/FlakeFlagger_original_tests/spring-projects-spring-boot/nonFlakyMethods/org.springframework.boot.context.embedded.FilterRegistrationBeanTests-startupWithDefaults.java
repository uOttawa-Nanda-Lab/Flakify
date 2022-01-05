@Test public void startupWithDefaults() throws Exception {
  FilterRegistrationBean bean=new FilterRegistrationBean(this.filter);
  bean.onStartup(this.servletContext);
  verify(this.servletContext).addFilter("mockFilter",this.filter);
  verify(this.registration).setAsyncSupported(true);
  verify(this.registration).addMappingForUrlPatterns(FilterRegistrationBean.ASYNC_DISPATCHER_TYPES,false,"/*");
}
