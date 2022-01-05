@Test public void testIsMatch3(){
  URL consumerUrl=URL.valueOf("dubbo://127.0.0.1:20880/com.xxx.XxxService?version=1.0.0&group=aa");
  URL providerUrl=URL.valueOf("http://127.0.0.1:8080/com.xxx.XxxService?version=1.0.0&group=test");
  assertFalse(UrlUtils.isMatch(consumerUrl,providerUrl));
}
