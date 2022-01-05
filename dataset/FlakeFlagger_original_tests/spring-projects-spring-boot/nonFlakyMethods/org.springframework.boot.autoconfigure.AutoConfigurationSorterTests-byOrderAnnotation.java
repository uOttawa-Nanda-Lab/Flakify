@Test public void byOrderAnnotation() throws Exception {
  List<String> actual=this.sorter.getInPriorityOrder(Arrays.asList(LOWEST,HIGHEST));
  assertThat(actual,nameMatcher(HIGHEST,LOWEST));
}
