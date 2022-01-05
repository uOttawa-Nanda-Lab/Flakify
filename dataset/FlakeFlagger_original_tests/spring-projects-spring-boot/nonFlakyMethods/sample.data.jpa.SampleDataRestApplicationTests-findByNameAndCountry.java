@Test public void findByNameAndCountry() throws Exception {
  this.mvc.perform(get("/api/cities/search/findByNameAndCountryAllIgnoringCase?name=Melbourne&country=Australia")).andExpect(status().isOk()).andExpect(jsonPath("_embedded.citys",hasSize(1)));
}
