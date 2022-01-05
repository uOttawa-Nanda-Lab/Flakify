/** 
 * Verify getting all headers
 * @throws Exception
 */
@Test public void getAllHeaders() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
      response.setHeader("a","a");
      response.setHeader("b","b");
      response.addHeader("a","another");
    }
  }
;
  Map<String,List<String>> headers=get(url).headers();
  assertEquals(headers.size(),5);
  assertEquals(headers.get("a").size(),2);
  assertTrue(headers.get("b").get(0).equals("b"));
}
