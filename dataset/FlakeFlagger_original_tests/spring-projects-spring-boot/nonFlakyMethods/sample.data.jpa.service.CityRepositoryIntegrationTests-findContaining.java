@Test public void findContaining(){
  Page<City> cities=this.repository.findByNameContainingAndCountryContainingAllIgnoringCase("","UK",new PageRequest(0,10));
  assertThat(cities.getTotalElements(),is(equalTo(3L)));
}
