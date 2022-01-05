@Test public void filterHasError(){
  this.filter.setErrorAttributes(new DefaultErrorAttributes());
  MockHttpServletRequest request=new MockHttpServletRequest("GET","/foo");
  MockHttpServletResponse response=new MockHttpServletResponse();
  response.setStatus(500);
  request.setAttribute("javax.servlet.error.exception",new IllegalStateException("Foo"));
  response.addHeader("Content-Type","application/json");
  Map<String,Object> trace=this.filter.getTrace(request);
  this.filter.enhanceTrace(trace,response);
  @SuppressWarnings("unchecked") Map<String,Object> map=(Map<String,Object>)trace.get("error");
  System.err.println(map);
  assertEquals("Foo",map.get("message").toString());
}
