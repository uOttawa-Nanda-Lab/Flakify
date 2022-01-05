@Test public void byAutoConfigureBefore() throws Exception {
  List<String> actual=this.sorter.getInPriorityOrder(Arrays.asList(X,Y,Z));
  assertThat(actual,nameMatcher(Z,Y,X));
}
