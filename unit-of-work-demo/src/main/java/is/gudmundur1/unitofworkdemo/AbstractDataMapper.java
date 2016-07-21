package is.gudmundur1.unitofworkdemo;

/**
 * Created by gudmundur on 21.7.2016.
 */
public abstract class AbstractDataMapper {
    public abstract void insert(DomainObject obj);
    public abstract void update(DomainObject obj);
    public abstract void delete(DomainObject obj);
}
