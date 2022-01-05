@Test public void testCloning() throws Exception {
  BasicHeaderElement orig=new BasicHeaderElement("name","value",new NameValuePair[]{new BasicNameValuePair("param1","value1"),new BasicNameValuePair("param2","value2")});
  BasicHeaderElement clone=(BasicHeaderElement)orig.clone();
  Assert.assertEquals(orig,clone);
}
