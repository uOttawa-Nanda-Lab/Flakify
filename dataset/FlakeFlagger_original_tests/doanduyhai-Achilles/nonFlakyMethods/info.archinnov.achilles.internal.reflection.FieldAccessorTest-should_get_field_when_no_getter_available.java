@Test public void should_get_field_when_no_getter_available() throws Exception {
  BeanWithNoGetterForField instance=new BeanWithNoGetterForField("name");
  Field nameField=BeanWithNoGetterForField.class.getDeclaredField("name");
  assertThat(accessor.getValueFromField(nameField,instance)).isEqualTo("name");
}
