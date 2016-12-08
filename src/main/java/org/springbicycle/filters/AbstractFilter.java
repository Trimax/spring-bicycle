package org.springbicycle.filters;


import org.springbicycle.filters.restrictions.Limitation;
import org.springbicycle.filters.restrictions.Sorting;

public abstract class AbstractFilter {
    public Limitation limitation;
    public Sorting sorting;
}
