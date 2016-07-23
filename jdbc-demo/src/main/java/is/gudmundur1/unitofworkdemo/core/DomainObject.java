package is.gudmundur1.unitofworkdemo.core;

import is.gudmundur1.unitofworkdemo.core.persistence.UnitOfWork;

public abstract class DomainObject {

    protected void markNew() {
        UnitOfWork.getCurrent().registerNew(this);
    }

    protected void markClean() {
        UnitOfWork.getCurrent().registerClean(this);
    }

    protected void markDirty() {
        UnitOfWork.getCurrent().registerDirty(this);
    }

    protected void markRemoved() {
        UnitOfWork.getCurrent().registerRemoved(this);
    }

    public abstract Object getId();
}
