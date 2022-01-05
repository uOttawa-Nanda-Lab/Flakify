@Test public void byAutoConfigureAfter() throws Exception {
  List<String> actual=this.sorter.getInPriorityOrder(Arrays.asList(A,B,C));
  assertThat(actual,nameMatcher(C,B,A));
}
