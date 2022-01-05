@Test public void byAutoConfigureMixedBeforeAndAfterWithDifferentInputOrder() throws Exception {
  List<String> actual=this.sorter.getInPriorityOrder(Arrays.asList(W,X,A,B,C));
  assertThat(actual,nameMatcher(C,W,B,A,X));
}
