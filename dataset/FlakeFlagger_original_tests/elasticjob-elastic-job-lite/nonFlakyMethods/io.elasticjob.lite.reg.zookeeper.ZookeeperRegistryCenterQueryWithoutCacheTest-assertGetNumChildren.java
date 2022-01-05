@Test public void assertGetNumChildren(){
  assertThat(zkRegCenter.getNumChildren("/test"),is(2));
  assertThat(zkRegCenter.getNumChildren("/test/deep"),is(1));
  assertThat(zkRegCenter.getNumChildren("/test/child"),is(0));
  assertThat(zkRegCenter.getNumChildren("/test/notExisted"),is(0));
}
