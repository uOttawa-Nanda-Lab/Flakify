@Test public void add_an_element_to_a_tuple(){
  Tuple tuple=new Tuple("Yoda",800);
  tuple.addData("Jedi");
  assertThat(tuple).isEqualTo(new Tuple("Yoda",800,"Jedi"));
}
