package org.springbicycle.entities.hierarchies;

import org.springbicycle.entities.AbstractEntity;

public abstract class AbstractHierarchicalEntity extends AbstractEntity {
    public String  parentID;
    public String  name;
    public Boolean isLeaf;
}
