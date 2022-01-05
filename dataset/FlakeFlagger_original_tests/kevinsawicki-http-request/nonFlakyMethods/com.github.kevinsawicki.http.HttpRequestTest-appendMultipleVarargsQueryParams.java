/** 
 * Append multiple params
 * @throws Exception
 */
@Test public void appendMultipleVarargsQueryParams() throws Exception {
  assertEquals("http://test.com/1?a=b&c=d",HttpRequest.append("http://test.com/1","a","b","c","d"));
}
