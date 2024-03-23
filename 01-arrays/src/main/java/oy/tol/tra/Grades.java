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
      int i = 0;
      while (i < grades.length/2) {
         Integer temp = grades[i];
         grades[i] = grades[grades.length-i-1];
         grades[grades.length-i-1] = temp;
         i++;
      }
   }

   public void sort() {
      int n = grades.length;
      for (int j = 1; j < n; j++) {
         int key = grades[j];
         int i = j - 1;
         while (i >= 0 && grades[i] > key) {
            grades[i + 1] = grades[i];
            i = i - 1;
         }
         grades[i + 1] = key;
      }
   }

   public Integer [] getArray() {
      return grades;
   }
}
