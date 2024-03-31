package admin;

import model.Audi;
import model.City;
import model.Theatre;

import java.util.ArrayList;
import java.util.List;

public class TheatreController {

    public List<Audi> addScreens(int screenCount){
        List<Audi> audiList = new ArrayList<>();
        while (screenCount > 0){
            //creating screen
            Audi audi = new Audi();

            //Adding seats
            audi.addSeats();
            audiList.add(audi);
            screenCount--;
        }
        return audiList;
    }
}
