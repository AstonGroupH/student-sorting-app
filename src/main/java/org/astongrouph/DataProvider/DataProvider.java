package org.astongrouph.DataProvider;

import org.astongrouph.CustomArray.CustomArrayList;
import org.astongrouph.model.Student;

public interface DataProvider {

    CustomArrayList<Student> provide(int count);

}