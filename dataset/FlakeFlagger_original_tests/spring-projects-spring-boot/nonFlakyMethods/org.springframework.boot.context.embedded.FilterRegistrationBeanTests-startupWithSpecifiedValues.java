@Test public void startupWithSpecifiedValues() throws Exception {
  FilterRegistrationBean bean=new FilterRegistrationBean();
  bean.setName("test");
  bean.setFilter(this.filter);
  bean.setAsyncSupported(false);
  bean.setInitParameters(Collections.singletonMap("a","b"));
  bean.addInitParameter("c","d");
  bean.setUrlPatterns(new LinkedHashSet<String>(Arrays.asList("/a","/b")));
  bean.addUrlPatterns("/c");
  bean.setServletNames(new LinkedHashSet<String>(Arrays.asList("s1","s2")));
  bean.addServletNames("s3");
  bean.setServletRegistrationBeans(Collections.singleton(mockServletRegistation("s4")));
  bean.addServletRegistrationBeans(mockServletRegistation("s5"));
  bean.setMatchAfter(true);
  bean.onStartup(this.servletContext);
  verify(this.servletContext).addFilter("test",this.filter);
  verify(this.registration).setAsyncSupported(false);
  Map<String,String> expectedInitParameters=new HashMap<String,String>();
  expectedInitParameters.put("a","b");
  expectedInitParameters.put("c","d");
  verify(this.registration).setInitParameters(expectedInitParameters);
  verify(this.registration).addMappingForUrlPatterns(FilterRegistrationBean.NON_ASYNC_DISPATCHER_TYPES,true,"/a","/b","/c");
  verify(this.registration).addMappingForServletNames(FilterRegistrationBean.NON_ASYNC_DISPATCHER_TYPES,true,"s4","s5","s1","s2","s3");
}
