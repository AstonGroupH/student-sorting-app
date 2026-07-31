package org.astongrouph.model;

import java.util.Objects;

public final class Student {
    public static class Builder {
        public Builder(int groupNumber, double averageScore, int recordBookNumber) {
            this.groupNumber = groupNumber;
            this.averageScore = averageScore;
            this.recordBookNumber = recordBookNumber;
        }

        public Builder() {
            this.groupNumber = 0;
            this.averageScore = 0.0;
            this.recordBookNumber = 0;
        }

        public Builder groupNumber(int groupNumber) {
            this.groupNumber = groupNumber;
            return this;
        }

        public Builder averageScore(double averageScore) {
            this.averageScore = averageScore;
            return this;
        }

        public Builder recordBookNumber(int recordBookNumber) {
            this.recordBookNumber = recordBookNumber;
            return this;
        }

        public Student build() {
            return new Student(this);
        }

        private int groupNumber;
        private double averageScore;
        private int recordBookNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        return groupNumber == student.groupNumber
                && averageScore == student.averageScore
                && recordBookNumber == student.recordBookNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupNumber, averageScore, recordBookNumber);
    }

    @Override
    public String toString() {
        return String.format("Student: [ group number : %d ] [ average score : %.2f ] [ record book number : %d ]",
                groupNumber, averageScore, recordBookNumber);
    }

    private Student(Builder builder) {
        this.groupNumber = builder.groupNumber;
        this.averageScore = builder.averageScore;
        this.recordBookNumber = builder.recordBookNumber;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public int getRecordBookNumber() {
        return recordBookNumber;
    }


    private final int groupNumber;
    private final double averageScore;
    private final int recordBookNumber;
}