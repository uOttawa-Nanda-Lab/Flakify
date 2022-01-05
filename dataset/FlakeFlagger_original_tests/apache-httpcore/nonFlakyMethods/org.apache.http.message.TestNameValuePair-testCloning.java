@Test public void testCloning() throws Exception {
  BasicNameValuePair orig=new BasicNameValuePair("name1","value1");
  BasicNameValuePair clone=(BasicNameValuePair)orig.clone();
  Assert.assertEquals(orig,clone);
}
