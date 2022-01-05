/** 
 * Append list parameter
 * @throws Exception
 */
@Test public void appendListQueryParams() throws Exception {
  assertEquals("http://test.com/?foo[]=bar&foo[]=baz",HttpRequest.append("http://test.com",Collections.singletonMap("foo",Arrays.asList(new String[]{"bar","baz"}))));
  assertEquals("http://test.com/?a[]=1&a[]=2",HttpRequest.append("http://test.com",Collections.singletonMap("a",Arrays.asList(new Integer[]{1,2}))));
  assertEquals("http://test.com/?a[]=1",HttpRequest.append("http://test.com",Collections.singletonMap("a",Arrays.asList(new Integer[]{1}))));
  assertEquals("http://test.com/?",HttpRequest.append("http://test.com",Collections.singletonMap("a",Arrays.asList(new Integer[]{}))));
  assertEquals("http://test.com/?foo[]=bar&foo[]=baz&a[]=1&a[]=2",HttpRequest.append("http://test.com","foo",Arrays.asList(new String[]{"bar","baz"}),"a",Arrays.asList(new Integer[]{1,2})));
}
