public void testRecordedRequestAccessors(){
  List<String> headers=Arrays.asList("User-Agent: okhttp","Cookie: s=square","Cookie: a=android","X-Whitespace:  left","X-Whitespace:right  ","X-Whitespace:  both  ");
  List<Integer> chunkSizes=Collections.emptyList();
  byte[] body={'A','B','C'};
  String requestLine="GET / HTTP/1.1";
  RecordedRequest request=new RecordedRequest(requestLine,headers,chunkSizes,body.length,body,0,null);
  assertEquals("s=square",request.getHeader("cookie"));
  assertEquals(Arrays.asList("s=square","a=android"),request.getHeaders("cookie"));
  assertEquals("left",request.getHeader("x-whitespace"));
  assertEquals(Arrays.asList("left","right","both"),request.getHeaders("x-whitespace"));
  assertEquals("ABC",request.getUtf8Body());
}
