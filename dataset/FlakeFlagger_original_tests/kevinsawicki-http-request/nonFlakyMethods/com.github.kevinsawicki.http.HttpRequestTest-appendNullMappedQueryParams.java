/** 
 * Append null params
 * @throws Exception
 */
@Test public void appendNullMappedQueryParams() throws Exception {
  assertEquals("http://test.com/1",HttpRequest.append("http://test.com/1",(Map<?,?>)null));
}
