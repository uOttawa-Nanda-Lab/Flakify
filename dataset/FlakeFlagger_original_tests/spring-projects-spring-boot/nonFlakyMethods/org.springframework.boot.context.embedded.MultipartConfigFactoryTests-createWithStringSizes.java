@Test public void createWithStringSizes() throws Exception {
  MultipartConfigFactory factory=new MultipartConfigFactory();
  factory.setMaxFileSize("1");
  factory.setMaxRequestSize("2kB");
  factory.setFileSizeThreshold("3Mb");
  MultipartConfigElement config=factory.createMultipartConfig();
  assertThat(config.getMaxFileSize(),equalTo(1L));
  assertThat(config.getMaxRequestSize(),equalTo(2 * 1024L));
  assertThat(config.getFileSizeThreshold(),equalTo(3 * 1024 * 1024));
}
