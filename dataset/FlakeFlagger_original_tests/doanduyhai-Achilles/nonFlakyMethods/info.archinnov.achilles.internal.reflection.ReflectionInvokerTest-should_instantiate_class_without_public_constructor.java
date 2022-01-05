@Test public void should_instantiate_class_without_public_constructor() throws Exception {
  BeanWithoutPublicConstructor instance=invoker.instantiate(BeanWithoutPublicConstructor.class);
  assertThat(instance).isInstanceOf(BeanWithoutPublicConstructor.class);
}
