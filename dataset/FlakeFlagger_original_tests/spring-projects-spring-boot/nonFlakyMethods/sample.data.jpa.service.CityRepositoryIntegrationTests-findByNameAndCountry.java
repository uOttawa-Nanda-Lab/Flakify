@Test public void findByNameAndCountry(){
  City city=this.repository.findByNameAndCountryAllIgnoringCase("Melbourne","Australia");
  assertThat(city,notNullValue());
  assertThat(city.getName(),is(equalTo("Melbourne")));
}
