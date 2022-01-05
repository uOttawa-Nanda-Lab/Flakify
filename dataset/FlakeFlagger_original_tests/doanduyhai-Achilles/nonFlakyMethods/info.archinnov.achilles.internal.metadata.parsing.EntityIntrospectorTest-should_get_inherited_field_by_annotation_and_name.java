@SuppressWarnings({"unchecked","rawtypes"}) @Test public void should_get_inherited_field_by_annotation_and_name() throws Exception {
  Field address=introspector.getInheritedPrivateFields(ChildBean.class,Column.class,"address");
  assertThat(address.getName()).isEqualTo("address");
  assertThat(address.getType()).isEqualTo((Class)String.class);
}
