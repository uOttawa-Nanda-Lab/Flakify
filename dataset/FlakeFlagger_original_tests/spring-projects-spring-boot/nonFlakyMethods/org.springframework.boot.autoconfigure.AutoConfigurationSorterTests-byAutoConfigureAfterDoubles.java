@Test public void byAutoConfigureAfterDoubles() throws Exception {
  List<String> actual=this.sorter.getInPriorityOrder(Arrays.asList(A,B,C,E));
  assertThat(actual,nameMatcher(C,E,B,A));
}
