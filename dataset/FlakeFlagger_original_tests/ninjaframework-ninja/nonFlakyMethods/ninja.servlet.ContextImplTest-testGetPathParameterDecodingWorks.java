@Test public void testGetPathParameterDecodingWorks(){
  context.init(httpServletRequest,httpServletResponse);
  Map<String,String> parameterMap=Maps.newHashMap();
  parameterMap.put("parameter","blue%2Fred%3Fand+green%E2%82%AC%2f");
  when(route.getPathParametersEncoded(Matchers.anyString())).thenReturn(parameterMap);
  context.setRoute(route);
  assertEquals("blue/red?and+green€/",context.getPathParameter("parameter"));
}
