@Test public void should_not_mark_dirty_on_retain_all_when_all_match() throws Exception {
  Set<Object> target=Sets.<Object>newHashSet("a","b","c");
  SetWrapper wrapper=prepareSetWrapper(target);
  wrapper.setProxifier(proxifier);
  Collection<String> list=Arrays.asList("a","b","c");
  when(proxifier.removeProxy(Mockito.<Collection<String>>any())).thenReturn(list);
  wrapper.retainAll(list);
  assertThat(target).containsOnly("a","b","c");
  assertThat(dirtyMap).isEmpty();
}
