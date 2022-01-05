/** 
 * Append null params
 * @throws Exception
 */
@Test public void appendNullVaragsQueryParams() throws Exception {
  assertEquals("http://test.com/1",HttpRequest.append("http://test.com/1",(Object[])null));
}
