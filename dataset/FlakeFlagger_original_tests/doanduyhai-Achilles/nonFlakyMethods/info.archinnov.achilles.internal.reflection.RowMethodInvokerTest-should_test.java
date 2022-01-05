@Test public void should_test() throws Exception {
  List<Object> rawValues=new ArrayList<Object>(Collections.nCopies(2,null));
  assertThat(rawValues.get(0)).isNull();
  assertThat(rawValues.get(1)).isNull();
}
