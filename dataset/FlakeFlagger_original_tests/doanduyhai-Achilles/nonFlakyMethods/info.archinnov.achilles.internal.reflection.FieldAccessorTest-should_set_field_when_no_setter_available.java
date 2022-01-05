@Test public void should_set_field_when_no_setter_available() throws Exception {
  BeanWithNoSetterForField instance=new BeanWithNoSetterForField("name");
  Field nameField=BeanWithNoSetterForField.class.getDeclaredField("name");
  accessor.setValueToField(nameField,instance,"new_name");
  assertThat(instance.getName()).isEqualTo("new_name");
}
