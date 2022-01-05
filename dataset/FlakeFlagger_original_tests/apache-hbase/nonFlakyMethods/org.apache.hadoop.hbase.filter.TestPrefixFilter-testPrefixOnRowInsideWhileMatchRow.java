public void testPrefixOnRowInsideWhileMatchRow() throws Exception {
  prefixRowTests(new WhileMatchFilter(this.mainFilter),true);
}
