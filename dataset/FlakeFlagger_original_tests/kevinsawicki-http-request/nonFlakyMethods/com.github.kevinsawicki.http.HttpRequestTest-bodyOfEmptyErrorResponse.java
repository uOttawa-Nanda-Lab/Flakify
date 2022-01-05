/** 
 * Verify reading response body for empty 400
 * @throws Exception
 */
@Test public void bodyOfEmptyErrorResponse() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_BAD_REQUEST);
    }
  }
;
  assertEquals("",get(url).body());
}
