/** 
 * Verify 'ETag' header
 * @throws Exception
 */
@Test public void eTagHeader() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
      response.setHeader("ETag","abcd");
    }
  }
;
  assertEquals("abcd",get(url).eTag());
}
