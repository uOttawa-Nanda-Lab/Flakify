@Test public void should_pass_when_selected_fields_and_nested_public_fields_are_equal(){
  Player rose=new Player(new Name("Derrick","Rose"),"Chicago Bulls");
  rose.nickname=new Name("Crazy","Duncks");
  Player jalen=new Player(new Name("Derrick","Coleman"),"Chicago Bulls");
  jalen.nickname=new Name("Crazy","Defense");
  objects.assertIsEqualToComparingOnlyGivenFields(someInfo(),rose,jalen,"team","nickname.first");
}
