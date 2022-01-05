@Test public void filterDumpsRequest(){
  MockHttpServletRequest request=new MockHttpServletRequest("GET","/foo");
  request.addHeader("Accept","application/json");
  Map<String,Object> trace=this.filter.getTrace(request);
  assertEquals("GET",trace.get("method"));
  assertEquals("/foo",trace.get("path"));
  @SuppressWarnings("unchecked") Map<String,Object> map=(Map<String,Object>)trace.get("headers");
  assertEquals("{Accept=application/json}",map.get("request").toString());
}
