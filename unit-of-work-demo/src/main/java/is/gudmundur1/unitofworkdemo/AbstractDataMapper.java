package is.gudmundur1.unitofworkdemo;

public abstract class AbstractDataMapper<T extends DomainObject> {
    public abstract void insert(T obj);
    public abstract void update(T obj);
    public abstract void delete(T obj);
}
