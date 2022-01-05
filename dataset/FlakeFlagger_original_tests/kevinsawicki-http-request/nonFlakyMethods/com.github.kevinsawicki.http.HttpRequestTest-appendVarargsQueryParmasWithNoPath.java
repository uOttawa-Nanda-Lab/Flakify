/** 
 * Append with base URL with no path
 * @throws Exception
 */
@Test public void appendVarargsQueryParmasWithNoPath() throws Exception {
  assertEquals("http://test.com/?a=b",HttpRequest.append("http://test.com","a","b"));
}
