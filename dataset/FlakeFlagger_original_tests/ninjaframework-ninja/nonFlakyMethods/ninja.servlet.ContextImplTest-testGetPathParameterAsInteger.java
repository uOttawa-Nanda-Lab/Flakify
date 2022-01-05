@Test public void testGetPathParameterAsInteger(){
  context.init(httpServletRequest,httpServletResponse);
  Map<String,String> parameterMap=Maps.newHashMap();
  parameterMap.put("parameter","parameter");
  when(route.getPathParametersEncoded(Matchers.anyString())).thenReturn(parameterMap);
  context.setRoute(route);
  assertEquals(null,context.getPathParameterAsInteger("parameter"));
  parameterMap.put("parameter","1");
  assertEquals(new Integer(1),context.getPathParameterAsInteger("parameter"));
}
