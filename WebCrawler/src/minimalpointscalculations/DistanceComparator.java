package minimalpointscalculations;

import java.util.Comparator;

public class DistanceComparator implements Comparator<Result> {

    @Override
    public int compare(Result arg0, Result arg1) {
        return arg0.getDistance().compareTo(arg1.getDistance());
    }

}
