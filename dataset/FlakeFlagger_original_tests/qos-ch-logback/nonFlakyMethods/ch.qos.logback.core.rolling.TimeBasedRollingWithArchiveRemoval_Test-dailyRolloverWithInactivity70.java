@Test public void dailyRolloverWithInactivity70(){
  int maxHistory=6;
  int simulatedNumberOfPeriods=70;
  int startInactivity=30;
  int numInactivityPeriods=1;
  generateDailyRollover(currentTime,maxHistory,simulatedNumberOfPeriods,startInactivity,numInactivityPeriods);
}
