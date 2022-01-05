@Test public void filterHasResponseStatus(){
  MockHttpServletRequest request=new MockHttpServletRequest("GET","/foo");
  MockHttpServletResponse response=new MockHttpServletResponse();
  response.setStatus(404);
  response.addHeader("Content-Type","application/json");
  Map<String,Object> trace=this.filter.getTrace(request);
  this.filter.enhanceTrace(trace,response);
  @SuppressWarnings("unchecked") Map<String,Object> map=(Map<String,Object>)((Map<String,Object>)trace.get("headers")).get("response");
  assertEquals("404",map.get("status").toString());
}
