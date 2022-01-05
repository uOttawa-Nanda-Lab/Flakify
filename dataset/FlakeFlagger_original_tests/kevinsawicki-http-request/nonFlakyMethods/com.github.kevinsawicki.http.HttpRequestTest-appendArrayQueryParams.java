/** 
 * Append array parameter
 * @throws Exception
 */
@Test public void appendArrayQueryParams() throws Exception {
  assertEquals("http://test.com/?foo[]=bar&foo[]=baz",HttpRequest.append("http://test.com",Collections.singletonMap("foo",new String[]{"bar","baz"})));
  assertEquals("http://test.com/?a[]=1&a[]=2",HttpRequest.append("http://test.com",Collections.singletonMap("a",new int[]{1,2})));
  assertEquals("http://test.com/?a[]=1",HttpRequest.append("http://test.com",Collections.singletonMap("a",new int[]{1})));
  assertEquals("http://test.com/?",HttpRequest.append("http://test.com",Collections.singletonMap("a",new int[]{})));
  assertEquals("http://test.com/?foo[]=bar&foo[]=baz&a[]=1&a[]=2",HttpRequest.append("http://test.com","foo",new String[]{"bar","baz"},"a",new int[]{1,2}));
}
