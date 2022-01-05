@Test public void byAutoConfigureMixedBeforeAndAfter() throws Exception {
  List<String> actual=this.sorter.getInPriorityOrder(Arrays.asList(A,B,C,W,X));
  assertThat(actual,nameMatcher(C,W,B,A,X));
}
