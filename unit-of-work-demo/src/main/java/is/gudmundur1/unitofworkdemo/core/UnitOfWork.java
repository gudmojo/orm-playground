package is.gudmundur1.unitofworkdemo.core;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

public class UnitOfWork {

    private List<DomainObject> newObjects = new ArrayList<>();
    private List<DomainObject> dirtyObjects = new ArrayList<>();
    private List<DomainObject> removedObjects = new ArrayList<>();

    private static ThreadLocal<UnitOfWork> current = new ThreadLocal<>();

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
        insertNew();
        updateDirty();
        deleteRemoved();
    }

    private void insertNew() {
        for (DomainObject obj : newObjects) {
            MapperRegistry.getMapper(obj.getClass()).insert(obj);
        }
    }

    private void updateDirty() {
        for (DomainObject obj : dirtyObjects) {
            MapperRegistry.getMapper(obj.getClass()).update(obj);
        }
    }

    private void deleteRemoved() {
        for (DomainObject obj : removedObjects) {
            MapperRegistry.getMapper(obj.getClass()).delete(obj);
        }
    }
}
