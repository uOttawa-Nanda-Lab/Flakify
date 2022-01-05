@Test public void assertIsNotLocalInstancePath(){
  assertFalse(instanceNode.isLocalInstancePath("/test_job/instances/127.0.0.2@-@0"));
}
