@Test public void then_Iterable(){
  Iterable<String> iterable=Arrays.asList("1");
  then(iterable).contains("1");
}
