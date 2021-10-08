package com.homestask.parse.db;

import org.hibernate.Session;

public interface DBase {
    Session getCurrentSession();

    void closeCurrentSession();
}