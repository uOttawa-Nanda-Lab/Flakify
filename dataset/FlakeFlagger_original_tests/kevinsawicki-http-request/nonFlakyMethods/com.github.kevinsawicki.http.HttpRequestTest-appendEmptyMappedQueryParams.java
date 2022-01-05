/** 
 * Append empty params
 * @throws Exception
 */
@Test public void appendEmptyMappedQueryParams() throws Exception {
  assertEquals("http://test.com/1",HttpRequest.append("http://test.com/1",Collections.<String,String>emptyMap()));
}
