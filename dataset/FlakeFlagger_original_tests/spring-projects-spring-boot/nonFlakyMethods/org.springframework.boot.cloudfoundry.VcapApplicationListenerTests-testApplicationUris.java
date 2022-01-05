@Test public void testApplicationUris(){
  EnvironmentTestUtils.addEnvironment(this.context,"VCAP_APPLICATION:{\"instance_id\":\"bb7935245adf3e650dfb7c58a06e9ece\",\"instance_index\":0,\"uris\":[\"foo.cfapps.io\"]}");
  this.initializer.onApplicationEvent(this.event);
  assertEquals("foo.cfapps.io",this.context.getEnvironment().getProperty("vcap.application.uris[0]"));
}
