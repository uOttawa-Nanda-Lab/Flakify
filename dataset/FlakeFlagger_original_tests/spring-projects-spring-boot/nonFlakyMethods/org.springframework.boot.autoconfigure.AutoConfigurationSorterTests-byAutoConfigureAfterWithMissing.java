@Test public void byAutoConfigureAfterWithMissing() throws Exception {
  List<String> actual=this.sorter.getInPriorityOrder(Arrays.asList(A,B));
  assertThat(actual,nameMatcher(B,A));
}
