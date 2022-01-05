@Test public void filterEmptyTest() throws Exception {
  try {
    AbstractRegistry.filterEmpty(null,null);
    Assert.fail();
  }
 catch (  Exception e) {
    Assert.assertTrue(e instanceof NullPointerException);
  }
  List<URL> urls=new ArrayList<>();
  try {
    AbstractRegistry.filterEmpty(null,urls);
    Assert.fail();
  }
 catch (  Exception e) {
    Assert.assertTrue(e instanceof NullPointerException);
  }
  urls.add(testUrl.setProtocol(Constants.EMPTY_PROTOCOL));
  Assert.assertEquals(AbstractRegistry.filterEmpty(testUrl,null),urls);
  List<URL> testUrls=new ArrayList<>();
  Assert.assertEquals(AbstractRegistry.filterEmpty(testUrl,testUrls),urls);
  testUrls.add(testUrl);
  Assert.assertEquals(AbstractRegistry.filterEmpty(testUrl,testUrls),testUrls);
}
