@Test public void then_Iterator(){
  Iterator<String> iterator=Arrays.asList("1").iterator();
  then(iterator).contains("1");
}
