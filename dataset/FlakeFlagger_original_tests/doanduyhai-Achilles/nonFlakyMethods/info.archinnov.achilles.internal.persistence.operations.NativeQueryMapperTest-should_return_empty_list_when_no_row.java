@Test public void should_return_empty_list_when_no_row() throws Exception {
  List<TypedMap> result=mapper.mapRows(new ArrayList<Row>());
  assertThat(result).isEmpty();
  verifyZeroInteractions(cqlRowInvoker);
}
