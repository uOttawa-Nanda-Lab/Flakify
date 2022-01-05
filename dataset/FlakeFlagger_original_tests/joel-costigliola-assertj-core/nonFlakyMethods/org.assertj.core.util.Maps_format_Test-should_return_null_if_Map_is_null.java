@Test public void should_return_null_if_Map_is_null(){
  assertNull(Maps.format(standardRepresentation,null));
  assertNull(Maps.format(null));
}
