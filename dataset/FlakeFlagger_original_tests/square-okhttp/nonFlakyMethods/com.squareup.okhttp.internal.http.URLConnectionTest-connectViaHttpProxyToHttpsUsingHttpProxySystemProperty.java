@Test public void connectViaHttpProxyToHttpsUsingHttpProxySystemProperty() throws Exception {
  testConnectViaDirectProxyToHttps(ProxyConfig.HTTP_PROXY_SYSTEM_PROPERTY);
}
