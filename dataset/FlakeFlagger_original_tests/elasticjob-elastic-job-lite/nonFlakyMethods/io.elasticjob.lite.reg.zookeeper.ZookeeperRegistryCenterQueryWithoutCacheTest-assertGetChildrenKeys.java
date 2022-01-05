@Test public void assertGetChildrenKeys(){
  assertThat(zkRegCenter.getChildrenKeys("/test"),is(Arrays.asList("deep","child")));
  assertThat(zkRegCenter.getChildrenKeys("/test/deep"),is(Collections.singletonList("nested")));
  assertThat(zkRegCenter.getChildrenKeys("/test/child"),is(Collections.<String>emptyList()));
  assertThat(zkRegCenter.getChildrenKeys("/test/notExisted"),is(Collections.<String>emptyList()));
}
