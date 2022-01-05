@Test public void assertIsNotInstancePath(){
  assertFalse(instanceNode.isInstancePath("/test_job/other/127.0.0.1@-@0"));
}
