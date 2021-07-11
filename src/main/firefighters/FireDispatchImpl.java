package main.firefighters;

import java.util.ArrayList;
import java.util.List;

import main.api.Building;
import main.api.City;
import main.api.CityNode;
import main.api.FireDispatch;
import main.api.Firefighter;

import main.api.Common.Common;
import main.api.exceptions.NoFireFoundException;
import main.api.exceptions.OutOfCityBoundsException;

public class FireDispatchImpl implements FireDispatch {
  private City city;
  private ArrayList<Firefighter> firefighters = new ArrayList<>();
  private Building fireStation;

  public FireDispatchImpl(City city) {
    // Specify location of fires dispatch, which should be location of firestation
    this.city = city;
    this.fireStation = city.getFireStation();
  }

  @Override
  public void setFirefighters(int numFirefighters) {
    // Create list of firefighters containing number of firefighters specified
    for (int i = 1; i <= numFirefighters; i++) {
      firefighters.add(new FirefighterImpl(fireStation.getLocation()));
    }
  }

  @Override
  public List<Firefighter> getFirefighters() {
    return firefighters;
  }

  @Override
  public void dispatchFirefighers(CityNode... burningBuildings) throws OutOfCityBoundsException, NoFireFoundException {
    List<Firefighter> allFirefighters = getFirefighters();
    // Iterate through every burning building
    for (CityNode building : burningBuildings) {
      /*
       * Calculate the first firefighter in the list distance to burning building and
       * set it to be the closes firefighter to the next burning building
       */
      Firefighter closesFirefighter = allFirefighters.get(0);
      int closesFirefighterDistance = Common.calculateDistance(closesFirefighter.getLocation().getX(),
          closesFirefighter.getLocation().getY(), building.getX(), building.getY());
      for (int i = 1; i < allFirefighters.size(); i++) {
        // Calculate how far each firefighter is from the burning building
        int otherFirefighterDistance = Common.calculateDistance(allFirefighters.get(i).getLocation().getX(),
            allFirefighters.get(i).getLocation().getY(), building.getX(), building.getY());
        /*
         * Determine which firefighter is closer to the burning building then set that
         * firefighter as the closes
         */
        if (otherFirefighterDistance < closesFirefighterDistance) {
          closesFirefighter = allFirefighters.get(i);
          closesFirefighterDistance = otherFirefighterDistance;
        }
      }
      // Put firefighter at the burning building location and extinguish the building
      closesFirefighter.setLocation(building);
      city.getBuilding(building).extinguishFire();
    }
  }
}
