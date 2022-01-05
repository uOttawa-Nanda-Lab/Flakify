@Test public void should_return_true_when_has_embedded_id() throws Exception {
  PropertyMeta idMeta=new PropertyMeta();
  idMeta.setType(EMBEDDED_ID);
  EntityMeta meta=new EntityMeta();
  meta.setIdMeta(idMeta);
  assertThat(meta.hasEmbeddedId()).isTrue();
}
