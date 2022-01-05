/** 
 * Append with base URL already containing a '?'
 */
@Test public void appendWithVarargsQueryParamsWithExistingParams(){
  assertEquals("http://test.com/1?a=b&c=d",HttpRequest.append("http://test.com/1?a=b","c","d"));
  assertEquals("http://test.com/1?a=b&c=d",HttpRequest.append("http://test.com/1?a=b&","c","d"));
}
