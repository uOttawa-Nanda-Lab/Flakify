/** 
 * Append multiple params
 * @throws Exception
 */
@Test public void appendMultipleMappedQueryParams() throws Exception {
  Map<String,Object> params=new LinkedHashMap<String,Object>();
  params.put("a","b");
  params.put("c","d");
  assertEquals("http://test.com/1?a=b&c=d",HttpRequest.append("http://test.com/1",params));
}
