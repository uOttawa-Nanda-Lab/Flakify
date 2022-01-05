@Test public void should_init_proxy_warmup() throws Exception {
  ConfigMap params=new ConfigMap();
  params.put(PROXIES_WARM_UP_DISABLED,false);
  boolean actual=extractor.initProxyWarmUp(params);
  assertThat(actual).isFalse();
}
