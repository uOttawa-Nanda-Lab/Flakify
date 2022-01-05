@Test public void should_return_class_names(){
  String[] e={String.class.getName(),Integer.class.getName()};
  String[] a=Objects.namesOf(String.class,Integer.class);
  assertTrue(format("expected:<%s> but got:<%s>",Arrays.toString(e),Arrays.toString(a)),Arrays.equals(e,a));
}
