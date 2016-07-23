package is.gudmundur1.jdbcdemo.core.persistence;

import com.google.common.base.Preconditions;
import is.gudmundur1.jdbcdemo.core.CoreServiceRegistry;
import is.gudmundur1.jdbcdemo.core.DomainObject;

import java.util.ArrayList;
import java.util.List;

public class UnitOfWork {

    private List<DomainObject> newObjects = new ArrayList<>();
    private List<DomainObject> dirtyObjects = new ArrayList<>();
    private List<DomainObject> removedObjects = new ArrayList<>();
    private TransactionContext transactionContext;

    private static ThreadLocal<UnitOfWork> current = new ThreadLocal<>();

    public UnitOfWork() {
        this.transactionContext = CoreServiceRegistry.getTransactionContextFactory().create();
    }

    public static void newCurrent() {
        setCurrent(new UnitOfWork());
    }

    public static void setCurrent(UnitOfWork uow) {
        current.set(uow);
    }

    public static UnitOfWork getCurrent() {
        return current.get();
    }

    public void registerNew(DomainObject obj) {
        Preconditions.checkNotNull(obj.getId(), "id not null");
        Preconditions.checkArgument(!dirtyObjects.contains(obj), "object not dirty");
        Preconditions.checkArgument(!removedObjects.contains(obj), "object not removed");
        Preconditions.checkArgument(!newObjects.contains(obj), "object not already registered new");
        newObjects.add(obj);
    }

    public void registerDirty(DomainObject obj) {
        Preconditions.checkNotNull(obj.getId(), "id not null");
        Preconditions.checkArgument(!removedObjects.contains(obj), "object not removed");
        if (!dirtyObjects.contains(obj) && !newObjects.contains(obj)) {
            dirtyObjects.add(obj);
        }
    }

    public void registerRemoved(DomainObject obj) {
        Preconditions.checkNotNull(obj.getId(), "id not null");
        if (newObjects.remove(obj)) {
            return;
        }
        dirtyObjects.remove(obj);
        if (!removedObjects.contains(obj)) {
            removedObjects.add(obj);
        }
    }

    public void registerClean(DomainObject obj) {
        Preconditions.checkNotNull(obj.getId(), "id not null");
    }

    public void commit() {
        insertNew(transactionContext);
        updateDirty(transactionContext);
        deleteRemoved(transactionContext);
        transactionContext.commit();
    }

    private void insertNew(TransactionContext transactionContext) {
        for (DomainObject obj : newObjects) {
            System.out.println("Insert " + obj.getClass().getSimpleName() + ":" + obj.getId());
            MapperRegistry.getMapper(obj.getClass()).insert(obj, transactionContext);
        }
    }

    private void updateDirty(TransactionContext transactionContext) {
        for (DomainObject obj : dirtyObjects) {
            System.out.println("Update " + obj.getClass().getSimpleName() + ":" + obj.getId());
            MapperRegistry.getMapper(obj.getClass()).update(obj, transactionContext);
        }
    }

    private void deleteRemoved(TransactionContext transactionContext) {
        for (DomainObject obj : removedObjects) {
            System.out.println("Delete " + obj.getClass().getSimpleName() + ":" + obj.getId());
            MapperRegistry.getMapper(obj.getClass()).delete(obj, transactionContext);
        }
    }

    public TransactionContext getTransactionContext() {
        return transactionContext;
    }
}
