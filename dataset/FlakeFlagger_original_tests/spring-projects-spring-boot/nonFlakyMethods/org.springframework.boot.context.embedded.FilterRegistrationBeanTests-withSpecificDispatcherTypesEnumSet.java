@Test public void withSpecificDispatcherTypesEnumSet() throws Exception {
  FilterRegistrationBean bean=new FilterRegistrationBean(this.filter);
  EnumSet<DispatcherType> types=EnumSet.of(DispatcherType.INCLUDE,DispatcherType.FORWARD);
  bean.setDispatcherTypes(types);
  bean.onStartup(this.servletContext);
  verify(this.registration).addMappingForUrlPatterns(types,false,"/*");
}
