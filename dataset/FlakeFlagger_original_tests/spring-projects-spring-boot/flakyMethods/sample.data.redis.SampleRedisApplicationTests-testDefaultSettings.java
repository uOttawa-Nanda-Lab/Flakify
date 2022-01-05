@Test public void testDefaultSettings() throws Exception {
  try {
    SampleRedisApplication.main(new String[0]);
  }
 catch (  IllegalStateException ex) {
    if (!redisServerRunning(ex)) {
      return;
    }
  }
  String output=this.outputCapture.toString();
  assertTrue("Wrong output: " + output,output.contains("Found key spring.boot.redis.test"));
}
