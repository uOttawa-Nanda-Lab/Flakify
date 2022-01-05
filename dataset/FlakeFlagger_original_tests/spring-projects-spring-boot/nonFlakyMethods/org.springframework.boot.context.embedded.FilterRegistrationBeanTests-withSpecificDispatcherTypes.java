@Test public void withSpecificDispatcherTypes() throws Exception {
  FilterRegistrationBean bean=new FilterRegistrationBean(this.filter);
  bean.setDispatcherTypes(DispatcherType.INCLUDE,DispatcherType.FORWARD);
  bean.onStartup(this.servletContext);
  verify(this.registration).addMappingForUrlPatterns(EnumSet.of(DispatcherType.INCLUDE,DispatcherType.FORWARD),false,"/*");
}
