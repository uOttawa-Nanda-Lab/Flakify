@Test public void testCloning() throws Exception {
  BasicHeader orig=new BasicHeader("name1","value1");
  BasicHeader clone=(BasicHeader)orig.clone();
  Assert.assertEquals(orig.getName(),clone.getName());
  Assert.assertEquals(orig.getValue(),clone.getValue());
}
