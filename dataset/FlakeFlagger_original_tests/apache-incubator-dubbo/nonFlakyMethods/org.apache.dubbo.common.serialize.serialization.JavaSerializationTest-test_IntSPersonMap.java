@Test public void test_IntSPersonMap() throws Exception {
  Map<Integer,SerializablePerson> args=new HashMap<Integer,SerializablePerson>();
  args.put(1,new SerializablePerson());
  assertObject(args);
}
