@Test public void filterDumpsResponse(){
  MockHttpServletRequest request=new MockHttpServletRequest("GET","/foo");
  MockHttpServletResponse response=new MockHttpServletResponse();
  response.addHeader("Content-Type","application/json");
  Map<String,Object> trace=this.filter.getTrace(request);
  this.filter.enhanceTrace(trace,response);
  @SuppressWarnings("unchecked") Map<String,Object> map=(Map<String,Object>)trace.get("headers");
  assertEquals("{Content-Type=application/json, status=200}",map.get("response").toString());
}
