@Test public void test_pojo() throws Exception {
  assertObject(new Person());
  assertObject(new SerializablePerson());
}
