package oy.tol.tra;

public class Grades {

   private Integer [] grades = null;

   public Grades(Integer [] grades) {
      this.grades = new Integer [grades.length];
      for (int counter = 0; counter < grades.length; counter++) {
         this.grades[counter] = grades[counter];
      }
   }

   public void reverse() {
      Algorithms.reverse(this.grades);
   }

   public void sort() {
      Algorithms.sort(this.grades);

   }

   public Integer [] getArray() {
      return grades;
   }
}
