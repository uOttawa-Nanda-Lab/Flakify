/** 
 * Get header parameter values
 * @throws Exception
 */
@Test public void getMixQuotedParameters() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
      response.setHeader("a","value; b=c; d=\"e\"");
    }
  }
;
  HttpRequest request=get(url);
  assertTrue(request.ok());
  Map<String,String> params=request.parameters("a");
  assertNotNull(params);
  assertEquals(2,params.size());
  assertEquals("c",params.get("b"));
  assertEquals("e",params.get("d"));
}
