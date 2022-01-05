/** 
 * Append with base URL already containing a '?'
 */
@Test public void appendVarargsQueryParamsWithExistingQueryStart(){
  assertEquals("http://test.com/1?a=b",HttpRequest.append("http://test.com/1?","a","b"));
}
