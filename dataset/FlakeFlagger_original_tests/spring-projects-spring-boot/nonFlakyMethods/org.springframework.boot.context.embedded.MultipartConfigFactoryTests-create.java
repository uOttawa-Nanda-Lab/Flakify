@Test public void create() throws Exception {
  MultipartConfigFactory factory=new MultipartConfigFactory();
  factory.setLocation("loc");
  factory.setMaxFileSize(1);
  factory.setMaxRequestSize(2);
  factory.setFileSizeThreshold(3);
  MultipartConfigElement config=factory.createMultipartConfig();
  assertThat(config.getLocation(),equalTo("loc"));
  assertThat(config.getMaxFileSize(),equalTo(1L));
  assertThat(config.getMaxRequestSize(),equalTo(2L));
  assertThat(config.getFileSizeThreshold(),equalTo(3));
}
