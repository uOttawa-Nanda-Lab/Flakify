@Test public void lookupTest() throws Exception {
  try {
    abstractRegistry.lookup(null);
    Assert.fail();
  }
 catch (  Exception e) {
    Assert.assertTrue(e instanceof NullPointerException);
  }
  List<URL> urlList1=abstractRegistry.lookup(testUrl);
  Assert.assertFalse(urlList1.contains(testUrl));
  List<URL> urls=new ArrayList<>();
  urls.add(testUrl);
  abstractRegistry.notify(urls);
  List<URL> urlList2=abstractRegistry.lookup(testUrl);
  Assert.assertTrue(urlList2.contains(testUrl));
}
