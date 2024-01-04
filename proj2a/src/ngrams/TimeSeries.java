package ngrams;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    public static final int MIN_YEAR = 1400;
    public static final int MAX_YEAR = 2100;

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        this.putAll(ts.subMap(startYear, endYear));
    }

    /**
     * Returns all years for this TimeSeries (in any order).
     */
    public List<Integer> years() {
        List<Integer> yearList = new ArrayList<>();
        for (Map.Entry<Integer, Double> entry : this.entrySet()){
            yearList.add(entry.getKey());
        }
        return yearList;
    }

    /**
     * Returns all data for this TimeSeries (in any order).
     * Must be in the same order as years().
     */
    public List<Double> data() {
        List<Double> dataList = new ArrayList<>();
        for (Map.Entry<Integer, Double> entry : this.entrySet()){
            dataList.add(entry.getValue());
        }
        return dataList;
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {
        if (this.size() == 0 && ts.size() == 0){
            return new TimeSeries();
        }
        TimeSeries returnTs = new TimeSeries();
        returnTs.putAll(this);
        for (Map.Entry<Integer, Double> entry : ts.entrySet()){
            if (returnTs.containsKey(entry.getKey())) {
                returnTs.put(entry.getKey(), this.get(entry.getKey()) + entry.getValue());
            } else {
                returnTs.put(entry.getKey(), entry.getValue());
            }
        }
        return returnTs;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        TimeSeries returnTs = new TimeSeries();
        for (Map.Entry<Integer, Double> entry : this.entrySet()){
            if (!ts.containsKey(entry.getKey())) {
                throw new IllegalArgumentException();
            }
            returnTs.put(entry.getKey(), entry.getValue() / ts.get(entry.getKey()));
        }
        return returnTs;
    }

}
