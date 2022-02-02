package edu.nc.tasks.tasklist_spring.utils;

public class JDBCException extends StorageException {
    public JDBCException(String e) {
        super(e);
    }
}
