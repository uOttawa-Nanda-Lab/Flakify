@Test public void should_infer_value_class_from_list() throws Exception {
@SuppressWarnings("unused") class Test {
    private List<String> friends;
  }
  Type type=Test.class.getDeclaredField("friends").getGenericType();
  Class<String> infered=parser.inferValueClassForListOrSet(type,Test.class);
  assertThat(infered).isEqualTo(String.class);
}
