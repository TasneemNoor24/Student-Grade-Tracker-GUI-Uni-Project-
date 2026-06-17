public class Student extends Person {

    private int id;
    private double marks;

    public Student(int id, String name, double marks) {
        super(name);
        this.id = id;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public String calculateGrade() {

        GradeCalculator gc = new GradeCalculator() {

            @Override
            public String calculateGrade(double marks) {

                if (marks >= 90)
                    return "A+";
                else if (marks >= 80)
                    return "A";
                else if (marks >= 70)
                    return "B";
                else if (marks >= 60)
                    return "C";
                else if (marks >= 50)
                    return "D";
                else
                    return "F";
            }
        };

        return gc.calculateGrade(marks);
    }
}