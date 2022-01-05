@Test public void should_succeed_if_actual_has_no_parent() throws IOException {
  when(actual.getParent()).thenReturn(null);
  paths.assertHasNoParentRaw(info,actual);
}
