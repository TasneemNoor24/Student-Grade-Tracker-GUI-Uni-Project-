import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class StudentGradeTrackerGUI extends JFrame {

    private JTextField idField;
    private JTextField nameField;
    private JTextField marksField;

    private JTable table;
    private DefaultTableModel model;

    private ArrayList<Student> students;

    public StudentGradeTrackerGUI() {

        students = new ArrayList<>();

        setTitle("Student Grade Tracker");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        inputPanel.add(new JLabel("Student ID"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Student Name"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Marks"));
        marksField = new JTextField();
        inputPanel.add(marksField);

        JButton addButton = new JButton("Add Student");
        JButton clearButton = new JButton("Clear");

        inputPanel.add(addButton);
        inputPanel.add(clearButton);

        add(inputPanel, BorderLayout.NORTH);

        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Marks");
        model.addColumn("Grade");

        table = new JTable(model);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();

        JButton averageButton = new JButton("Calculate Average");
        JButton deleteButton = new JButton("Delete Student");

        bottomPanel.add(averageButton);
        bottomPanel.add(deleteButton);

        add(bottomPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addStudent());

        clearButton.addActionListener(e -> {
            idField.setText("");
            nameField.setText("");
            marksField.setText("");
        });

        averageButton.addActionListener(e -> calculateAverage());

        deleteButton.addActionListener(e -> deleteStudent());
    }

    private void addStudent() {

        try {

            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            double marks = Double.parseDouble(marksField.getText());

            Student student = new Student(id, name, marks);

            students.add(student);

            model.addRow(new Object[]{
                    student.getId(),
                    student.getName(),
                    student.getMarks(),
                    student.calculateGrade()
            });

            JOptionPane.showMessageDialog(this,
                    "Student Added Successfully");

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this,
                    "Invalid Input");
        }
    }

    private void calculateAverage() {

        if (students.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No Students Found");
            return;
        }

        double total = 0;

        for (Student s : students) {
            total += s.getMarks();
        }

        double average = total / students.size();

        JOptionPane.showMessageDialog(this,
                "Class Average = " + average);
    }

    private void deleteStudent() {

        int row = table.getSelectedRow();

        if (row >= 0) {

            students.remove(row);
            model.removeRow(row);

            JOptionPane.showMessageDialog(this,
                    "Student Deleted");
        }
        else {

            JOptionPane.showMessageDialog(this,
                    "Select a Student First");
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new StudentGradeTrackerGUI().setVisible(true);
        });
    }
}