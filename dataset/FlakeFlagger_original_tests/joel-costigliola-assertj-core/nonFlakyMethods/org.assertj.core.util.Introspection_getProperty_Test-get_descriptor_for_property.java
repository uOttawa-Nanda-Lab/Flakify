@Test public void get_descriptor_for_property(){
  PropertyDescriptor propertyDescriptor=getProperty("age",judy);
  assertThat(propertyDescriptor).isNotNull();
  assertThat(propertyDescriptor.getName()).isEqualTo("age");
}
