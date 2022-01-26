package edu.nc.tasks.storages;

import edu.nc.tasks.utils.StorageException;

import java.util.List;

public interface Storage<T, K> {
    void insert(T a) throws StorageException;
    void update(T a) throws StorageException;
    void delete(K a) throws StorageException;

    boolean exists(K a) throws StorageException;
    T get(K a) throws StorageException;
    List<T> getAll() throws StorageException;
}