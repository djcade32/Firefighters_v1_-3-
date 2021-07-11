package main.firefighters;

import main.api.CityNode;
import main.api.Firefighter;

import main.api.Common.Common;

public class FirefighterImpl implements Firefighter {

  private CityNode cityNode;
  private int lifeTimeTravelDistance = 0;

  public FirefighterImpl(CityNode cityNode) {
    this.cityNode = cityNode;
  }

  @Override
  public CityNode getLocation() {
    return cityNode;
  }

  @Override
  public void setLocation(CityNode cityNode) {
    lifeTimeTravelDistance += Common.calculateDistance(cityNode.getX(), cityNode.getY(), getLocation().getX(),
        getLocation().getY());
    this.cityNode = cityNode;
  }

  @Override
  public int distanceTraveled() {
    return lifeTimeTravelDistance;
  }
}
