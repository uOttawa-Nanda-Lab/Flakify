@Test public void test_SPersonSet() throws Exception {
  Set<SerializablePerson> args=new HashSet<SerializablePerson>();
  args.add(new SerializablePerson());
  assertObject(args);
}
