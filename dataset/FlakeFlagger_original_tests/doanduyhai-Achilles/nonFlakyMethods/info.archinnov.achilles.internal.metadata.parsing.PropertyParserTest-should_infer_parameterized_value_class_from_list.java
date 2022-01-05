@SuppressWarnings("rawtypes") @Test public void should_infer_parameterized_value_class_from_list() throws Exception {
@SuppressWarnings("unused") class Test {
    private List<Class<Void>> friends;
  }
  Type type=Test.class.getDeclaredField("friends").getGenericType();
  Class infered=parser.inferValueClassForListOrSet(type,Test.class);
  assertThat(infered).isEqualTo(Class.class);
}
