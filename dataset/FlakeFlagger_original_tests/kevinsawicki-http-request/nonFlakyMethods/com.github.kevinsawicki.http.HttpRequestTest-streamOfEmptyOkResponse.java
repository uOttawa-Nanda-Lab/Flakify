/** 
 * Verify reading response body for empty 200
 * @throws Exception
 */
@Test public void streamOfEmptyOkResponse() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(200);
    }
  }
;
  assertEquals("",get(url).body());
}
