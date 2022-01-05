@Test public void testManagerWithSchedulerAndUpdatePeriodSet() throws Exception {
  final HttpServletRequest request=Mockito.mock(HttpServletRequest.class);
  Mockito.when(request.getRequestURI()).thenReturn("/app/g1.css");
  final Context context=Context.webContext(request,Mockito.mock(HttpServletResponse.class,Mockito.RETURNS_DEEP_STUBS),Mockito.mock(FilterConfig.class));
  final WroConfiguration config=new WroConfiguration();
  config.setModelUpdatePeriod(1);
  config.setCacheUpdatePeriod(1);
  Context.set(context,config);
  managerFactory.create().process();
  Thread.sleep(1100);
}
