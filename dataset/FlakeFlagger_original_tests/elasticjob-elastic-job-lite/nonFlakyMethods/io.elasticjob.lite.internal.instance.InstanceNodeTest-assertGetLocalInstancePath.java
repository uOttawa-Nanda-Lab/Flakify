@Test public void assertGetLocalInstancePath(){
  assertThat(instanceNode.getLocalInstanceNode(),is("instances/127.0.0.1@-@0"));
}
