@Test public void recordsHttpInteractions() throws Exception {
  AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(Config.class,MetricFilterAutoConfiguration.class);
  Filter filter=context.getBean(Filter.class);
  final MockHttpServletRequest request=new MockHttpServletRequest("GET","/test/path");
  final MockHttpServletResponse response=new MockHttpServletResponse();
  FilterChain chain=mock(FilterChain.class);
  willAnswer(new Answer<Object>(){
    @Override public Object answer(    InvocationOnMock invocation) throws Throwable {
      response.setStatus(200);
      return null;
    }
  }
).given(chain).doFilter(request,response);
  filter.doFilter(request,response,chain);
  verify(context.getBean(CounterService.class)).increment("status.200.test.path");
  verify(context.getBean(GaugeService.class)).submit(eq("response.test.path"),anyDouble());
  context.close();
}
