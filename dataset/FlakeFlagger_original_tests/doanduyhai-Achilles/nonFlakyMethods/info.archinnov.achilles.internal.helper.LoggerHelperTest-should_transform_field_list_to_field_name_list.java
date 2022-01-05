@Test public void should_transform_field_list_to_field_name_list() throws Exception {
  Field field=CompleteBean.class.getDeclaredField("id");
  List<Field> fields=new ArrayList<Field>();
  fields.add(field);
  assertThat(Lists.transform(fields,LoggerHelper.fieldToStringFn)).contains("id");
}
