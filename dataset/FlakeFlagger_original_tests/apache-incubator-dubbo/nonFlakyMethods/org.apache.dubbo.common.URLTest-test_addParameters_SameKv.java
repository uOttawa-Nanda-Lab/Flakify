@Test public void test_addParameters_SameKv() throws Exception {
{
    URL url=URL.valueOf("dubbo://admin:hello1234@10.20.130.230:20880/context/path?application=morgan&k1=v1");
    URL newUrl=url.addParameters(CollectionUtils.toStringMap("k1","v1"));
    assertSame(url,newUrl);
  }
{
    URL url=URL.valueOf("dubbo://admin:hello1234@10.20.130.230:20880/context/path?application=morgan&k1=v1&k2=v2");
    URL newUrl=url.addParameters(CollectionUtils.toStringMap("k1","v1","k2","v2"));
    assertSame(newUrl,url);
  }
}
