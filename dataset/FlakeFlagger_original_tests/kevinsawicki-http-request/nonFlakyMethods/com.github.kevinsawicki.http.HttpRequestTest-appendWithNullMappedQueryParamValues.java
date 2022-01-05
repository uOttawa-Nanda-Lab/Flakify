/** 
 * Append params with null values
 * @throws Exception
 */
@Test public void appendWithNullMappedQueryParamValues() throws Exception {
  Map<String,Object> params=new LinkedHashMap<String,Object>();
  params.put("a",null);
  params.put("b",null);
  assertEquals("http://test.com/1?a=&b=",HttpRequest.append("http://test.com/1",params));
}
