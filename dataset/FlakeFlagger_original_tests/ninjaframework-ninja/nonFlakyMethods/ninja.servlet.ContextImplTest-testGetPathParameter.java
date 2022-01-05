@Test public void testGetPathParameter(){
  context.init(httpServletRequest,httpServletResponse);
  Map<String,String> parameterMap=Maps.newHashMap();
  parameterMap.put("parameter","parameter");
  when(route.getPathParametersEncoded(Matchers.anyString())).thenReturn(parameterMap);
  context.setRoute(route);
  assertEquals(null,context.getPathParameter("parameter_not_set"));
  assertEquals("parameter",context.getPathParameter("parameter"));
}
