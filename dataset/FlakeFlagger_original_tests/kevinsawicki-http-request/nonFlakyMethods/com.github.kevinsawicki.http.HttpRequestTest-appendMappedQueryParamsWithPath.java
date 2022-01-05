/** 
 * Append with base URL with path
 * @throws Exception
 */
@Test public void appendMappedQueryParamsWithPath() throws Exception {
  assertEquals("http://test.com/segment1?a=b",HttpRequest.append("http://test.com/segment1",Collections.singletonMap("a","b")));
  assertEquals("http://test.com/?a=b",HttpRequest.append("http://test.com/",Collections.singletonMap("a","b")));
}
