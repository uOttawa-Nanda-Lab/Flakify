/** 
 * Append empty params
 * @throws Exception
 */
@Test public void appendEmptyVarargsQueryParams() throws Exception {
  assertEquals("http://test.com/1",HttpRequest.append("http://test.com/1",new Object[0]));
}
