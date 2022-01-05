@Test public void testExport() throws Exception {
  service.export();
  assertThat(service.getExportedUrls(),hasSize(1));
  URL url=service.toUrl();
  assertThat(url.getProtocol(),equalTo("mockprotocol2"));
  assertThat(url.getPath(),equalTo(DemoService.class.getName()));
  assertThat(url.getParameters(),hasEntry(Constants.ANYHOST_KEY,"true"));
  assertThat(url.getParameters(),hasEntry(Constants.APPLICATION_KEY,"app"));
  assertThat(url.getParameters(),hasKey(Constants.BIND_IP_KEY));
  assertThat(url.getParameters(),hasKey(Constants.BIND_PORT_KEY));
  assertThat(url.getParameters(),hasEntry(Constants.DEFAULT_KEY + "." + Constants.EXPORT_KEY,"true"));
  assertThat(url.getParameters(),hasEntry(Constants.EXPORT_KEY,"true"));
  assertThat(url.getParameters(),hasEntry("echo.0.callback","false"));
  assertThat(url.getParameters(),hasEntry(Constants.GENERIC_KEY,"false"));
  assertThat(url.getParameters(),hasEntry(Constants.INTERFACE_KEY,DemoService.class.getName()));
  assertThat(url.getParameters(),hasKey(Constants.METHODS_KEY));
  assertThat(url.getParameters().get(Constants.METHODS_KEY),containsString("echo"));
  assertThat(url.getParameters(),hasEntry(Constants.SIDE_KEY,Constants.PROVIDER));
  Mockito.verify(protocolDelegate).export(Mockito.any(Invoker.class));
}
