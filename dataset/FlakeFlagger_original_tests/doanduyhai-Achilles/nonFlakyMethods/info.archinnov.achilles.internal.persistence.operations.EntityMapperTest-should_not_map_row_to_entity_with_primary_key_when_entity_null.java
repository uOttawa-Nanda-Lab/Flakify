@Test public void should_not_map_row_to_entity_with_primary_key_when_entity_null(){
  ClusteredEntity actual=entityMapper.mapRowToEntityWithPrimaryKey(entityMeta,row,null,true);
  assertThat(actual).isNull();
}
