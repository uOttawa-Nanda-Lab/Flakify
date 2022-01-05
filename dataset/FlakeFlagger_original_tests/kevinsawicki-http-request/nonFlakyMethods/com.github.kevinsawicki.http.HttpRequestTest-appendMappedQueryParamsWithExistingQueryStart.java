/** 
 * Append with base URL already containing a '?'
 */
@Test public void appendMappedQueryParamsWithExistingQueryStart(){
  assertEquals("http://test.com/1?a=b",HttpRequest.append("http://test.com/1?",Collections.singletonMap("a","b")));
}
