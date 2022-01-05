/** 
 * Append with base URL with path
 * @throws Exception
 */
@Test public void appendVarargsQueryParamsWithPath() throws Exception {
  assertEquals("http://test.com/segment1?a=b",HttpRequest.append("http://test.com/segment1","a","b"));
  assertEquals("http://test.com/?a=b",HttpRequest.append("http://test.com/","a","b"));
}
