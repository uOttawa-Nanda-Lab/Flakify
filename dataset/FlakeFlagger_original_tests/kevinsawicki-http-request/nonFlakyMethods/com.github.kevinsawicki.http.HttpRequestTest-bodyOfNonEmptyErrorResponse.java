/** 
 * Verify reading response body for non-empty 400
 * @throws Exception
 */
@Test public void bodyOfNonEmptyErrorResponse() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_BAD_REQUEST);
      try {
        response.getWriter().write("error");
      }
 catch (      IOException ignored) {
      }
    }
  }
;
  assertEquals("error",get(url).body());
}
