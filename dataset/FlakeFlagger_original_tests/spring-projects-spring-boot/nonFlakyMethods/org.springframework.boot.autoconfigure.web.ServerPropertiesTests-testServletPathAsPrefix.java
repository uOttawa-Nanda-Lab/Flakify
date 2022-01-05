@Test public void testServletPathAsPrefix() throws Exception {
  RelaxedDataBinder binder=new RelaxedDataBinder(this.properties,"server");
  binder.bind(new MutablePropertyValues(Collections.singletonMap("server.servletPath","/foo")));
  assertFalse(binder.getBindingResult().hasErrors());
  assertEquals("/foo/*",this.properties.getServletMapping());
  assertEquals("/foo",this.properties.getServletPrefix());
}
