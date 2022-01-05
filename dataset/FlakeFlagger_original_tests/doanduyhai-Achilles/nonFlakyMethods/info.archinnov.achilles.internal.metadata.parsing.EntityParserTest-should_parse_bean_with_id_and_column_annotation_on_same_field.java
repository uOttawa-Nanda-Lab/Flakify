@Test public void should_parse_bean_with_id_and_column_annotation_on_same_field() throws Exception {
  initEntityParsingContext(BeanWithIdAndColumnAnnotationsOnSameField.class);
  EntityMeta meta=parser.parseEntity(entityContext);
  assertThat(meta).isNotNull();
  assertThat(meta.getIdMeta().getPropertyName()).isEqualTo("toto");
}
