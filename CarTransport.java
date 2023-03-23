import java.awt.*;
import java.util.ArrayList;

/**
 * This is a subclass of truck that can transport cars
 */

public class CarTransport extends Truck {
    public ArrayList<Car> carsLoadedInCarTransport;
    private int maximumCapacity;

    /**
     * This is the constructor for Car Transport, it calls the constructor of the superclass truck and gives it to
     * the constructor in vehicle and initiates the object with Car transport specific variables.
     */

    public CarTransport(int maximumCapacity) {
        super(2, 125, 0, Color.blue, "Sweden Car Transport AB", 0, 0, 1);
        this.maximumCapacity = maximumCapacity;
        carsLoadedInCarTransport = new ArrayList<>();
    }

    /**
     * Raises the platform of the car transport by setting its current state of being up to true
     */
    public void raisePlatform() {
        super.checkPlatformMovementException();
        if (getCurrentSpeed() == 0 && !isPlatformIsUp()) {
            setPlatformIsUp(true);
        }
    }

    /**
     * Lowers the platform of the car transport by setting its current state of being up to false
     */
    public void lowerPlatform() {
        super.checkPlatformMovementException();
        if (getCurrentSpeed() == 0 && isPlatformIsUp()) {
            setPlatformIsUp(false);
        }
    }

    /**
     * This method adds a car as an element to the car transport-list if platform is lowered and car is stationary
     * @param carToLoad is the car that we load onto car transporter of the type Car
     * */

    public void loadCar(Car carToLoad) {
        if ((carToLoad.getyPosition() <= getyPosition()+1) && (carToLoad.getyPosition() >= getyPosition()-1) && (carToLoad.getxPosition() <= getxPosition()+1) && (carToLoad.getxPosition() >= getxPosition()-1))
        {
            if (!isPlatformIsUp() && getCurrentSpeed() == 0) {
                if (carsLoadedInCarTransport.size() <= maximumCapacity){
                    carsLoadedInCarTransport.add(carToLoad);
                    carToLoad.setyPosition(getyPosition());
                    carToLoad.setxPosition(getxPosition());
                }
                else{       //TODO throw exception
                    ;
                }

            }
        }
    }

    /**
     * Offload the car removes the last element in car from the list
     * */
    public void offloadCar(){
        if (!(carsLoadedInCarTransport.size() == 0 && !isPlatformIsUp() && getCurrentSpeed() == 0) ) {         //TODO exception
            carsLoadedInCarTransport.remove(carsLoadedInCarTransport.size()-1);
        }
    }

    /**
     * Throws an exception if we move with the platform up.
     */

    @Override
    public void move() {
        if (!isPlatformIsUp()){
            moveAllCars();
        }
        else {
            throw new MoveWithRaisedPlatformException("");
        }
    }

    /**
     *This method makes it possible for all the cars on the car transport to move together with
     * the car transport.
     */

    public void moveAllCars(){
        if (getDirection() == 1) {     //move north
            setyPosition(getyPosition()+ getCurrentSpeed());
            for (Car car : carsLoadedInCarTransport) {
                car.setyPosition(getyPosition());
            }
        } else if (getDirection() == 2) {    //move east
            setxPosition(getxPosition()+ getCurrentSpeed());
            for (Car car : carsLoadedInCarTransport) {
                car.setxPosition(getxPosition());
            }
        } else if (getDirection() == 3) {    //move south
            setyPosition(getyPosition()- getCurrentSpeed());
            for (Car car : carsLoadedInCarTransport) {
                car.setyPosition(getyPosition());
            }
        } else if (getDirection() == 4) {    //move west
            setxPosition(getxPosition()- getCurrentSpeed());
            for (Car car : carsLoadedInCarTransport) {
                car.setxPosition(getxPosition());
            }
        }
    }
}


