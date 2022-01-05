@Override @Test @SuppressWarnings("deprecation") public void should_have_internal_effects(){
  thrown.expect(UnsupportedOperationException.class);
  assertions.usingElementComparator(comparator);
}
