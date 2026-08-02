package org.astongrouph.DataProvider;

import org.astongrouph.collection.CustomArrayList;
import org.astongrouph.model.Student;

public interface DataProvider {

    CustomArrayList<Student> provide(int count);

}