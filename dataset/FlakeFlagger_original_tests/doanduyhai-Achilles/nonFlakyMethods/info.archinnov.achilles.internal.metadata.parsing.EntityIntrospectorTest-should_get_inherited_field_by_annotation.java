@SuppressWarnings({"unchecked","rawtypes"}) @Test public void should_get_inherited_field_by_annotation() throws Exception {
  Field id=introspector.getInheritedPrivateFields(ChildBean.class,Id.class);
  assertThat(id.getName()).isEqualTo("id");
  assertThat(id.getType()).isEqualTo((Class)Long.class);
}
