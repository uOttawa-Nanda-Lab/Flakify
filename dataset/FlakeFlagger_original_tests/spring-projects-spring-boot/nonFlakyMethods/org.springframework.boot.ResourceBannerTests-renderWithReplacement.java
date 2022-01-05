@Test public void renderWithReplacement() throws Exception {
  Resource resource=new ByteArrayResource("banner ${a} ${spring-boot.version} ${application.version}".getBytes());
  ResourceBanner banner=new ResourceBanner(resource);
  ConfigurableEnvironment environment=new MockEnvironment();
  Map<String,Object> source=Collections.<String,Object>singletonMap("a","1");
  environment.getPropertySources().addLast(new MapPropertySource("map",source));
  ByteArrayOutputStream out=new ByteArrayOutputStream();
  banner.printBanner(environment,getClass(),new PrintStream(out));
  assertThat(out.toString(),startsWith("banner 1"));
  assertThat(out.toString(),not(containsString("$")));
}
