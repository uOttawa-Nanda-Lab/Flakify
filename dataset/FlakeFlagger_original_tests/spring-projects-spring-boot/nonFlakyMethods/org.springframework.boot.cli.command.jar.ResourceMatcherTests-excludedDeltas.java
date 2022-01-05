@SuppressWarnings("unchecked") @Test public void excludedDeltas() throws Exception {
  ResourceMatcher resourceMatcher=new ResourceMatcher(Arrays.asList(""),Arrays.asList("-**/*.jar"));
  assertFalse(((Collection<String>)ReflectionTestUtils.getField(resourceMatcher,"excludes")).contains("**/*.jar"));
}
