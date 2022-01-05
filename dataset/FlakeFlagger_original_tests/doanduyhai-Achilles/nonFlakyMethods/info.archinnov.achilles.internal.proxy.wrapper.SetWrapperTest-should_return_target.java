@Test public void should_return_target() throws Exception {
  Set<Object> target=Sets.<Object>newHashSet("a");
  SetWrapper wrapper=new SetWrapper(target);
  assertThat(wrapper.getTarget()).isSameAs(target);
}
