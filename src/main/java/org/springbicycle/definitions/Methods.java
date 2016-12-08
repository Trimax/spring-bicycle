package org.springbicycle.definitions;

public final class Methods {
    /* Common methods */
    public final static String GET                = "/get";
    public final static String CREATE             = "/create";
    public final static String DELETE             = "/delete";
    public final static String UPDATE             = "/update";
    public final static String UPLOAD             = "/upload";

    /* Search methods */
    public final static String SEARCH             = "/search";
    public final static String COUNT              = "/count";

    /* Hierarchies */
    public final static String GET_CHILDREN = "/getChildren";
    public final static String GET_LEAVES   = "/getLeaves";
    public final static String SUGGEST      = "/suggest";
    public final static String LOOKUP       = "/lookup";

    /* Hierarchical filters */
    public final static String GET_TREE = "/getTree";

    /* Plain filters */
    public final static String GET_COLUMN_VALUES = "/getColumnValues";
}
