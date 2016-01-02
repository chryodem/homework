import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Driver {
    private String[] learningAlgorithms = {"clustering", "knn", "decisiontree", "backprop", "perceptron", "baseline"};
    private String[] evaluationMethod = {"training", "static", "random", "cross"};
    private String selectedAlgorithm = "";
    private String selectedTrainingMethod = "";
    private JComboBox algorithmsBox;
    private JComboBox trainingRuleBox;
    private JCheckBox chckbxNormalize;
    private JFrame frmMachineLearning;
    private String absolutePathTraining = "";
    private String absolutePathTesting = "";
    private JButton btnRun;
    private JButton btnReset;
    private JLabel lblFileNotSelected;
    private MLSystemManager ml;
    private JCheckBox chckbxVerbose;
    private JTextPane outputPane;
    private JLabel lblNotSelected;
    private JLabel lblNotSelected_1;
    private JScrollPane scrollPane;
    private JLabel lblTestFile;

    /**
     * Launch the application.
     * TODO replace AWT with JavaFX
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getLookAndFeel());
                Driver window = new Driver();

                window.frmMachineLearning.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the application.
     */
    public Driver() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmMachineLearning = new JFrame();
        frmMachineLearning.setTitle("Machine Learning");
        frmMachineLearning.setBounds(100, 100, 450, 400);
        frmMachineLearning.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmMachineLearning.getContentPane().setLayout(null);

        JButton btnSelectFile = new JButton("Select File");
        btnSelectFile.addActionListener(arg0 -> {
            FileDialog fd = new FileDialog((FileDialog) null, "open file",
                    FileDialog.LOAD);

            fd.setVisible(true);
            String fileName = fd.getFile();
            String filePath = fd.getDirectory();
            if (fileName != null && filePath != null) {
                absolutePathTraining = filePath + fileName;
                lblFileNotSelected.setText("File selected");

            }

            // System.out.println(filePath+fileName);
        });
        btnSelectFile.setBounds(10, 30, 100, 23);
        frmMachineLearning.getContentPane().add(btnSelectFile);

        lblFileNotSelected = new JLabel("File not selected");
        lblFileNotSelected.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblFileNotSelected.setBounds(10, 11, 100, 14);
        frmMachineLearning.getContentPane().add(lblFileNotSelected);

        algorithmsBox = new JComboBox(learningAlgorithms);
        algorithmsBox.addActionListener(e -> {
            JComboBox cb = (JComboBox) e.getSource();
            selectedAlgorithm = (String) cb.getSelectedItem();
            lblNotSelected.setText("Selected");
            lblNotSelected.setEnabled(true);
        });
        algorithmsBox.setBounds(10, 198, 100, 23);
        frmMachineLearning.getContentPane().add(algorithmsBox);

        trainingRuleBox = new JComboBox(evaluationMethod);
        trainingRuleBox.addActionListener(e -> {
            JComboBox cb = (JComboBox) e.getSource();
            selectedTrainingMethod = (String) cb.getSelectedItem();
            lblNotSelected_1.setText("Selected");
            lblNotSelected_1.setEnabled(true);

        });
        trainingRuleBox.setBounds(10, 259, 100, 23);
        frmMachineLearning.getContentPane().add(trainingRuleBox);

        chckbxNormalize = new JCheckBox("Normalize");

        chckbxNormalize.setBounds(13, 112, 97, 23);
        frmMachineLearning.getContentPane().add(chckbxNormalize);

        btnRun = new JButton("Run");
        btnRun.addActionListener(e -> {
            ml = new MLSystemManager();

            initializeForRun();
        });
        btnRun.setBounds(10, 294, 89, 23);
        frmMachineLearning.getContentPane().add(btnRun);

        btnReset = new JButton("Reset");
        btnReset.addActionListener(e -> resetInputs());
        btnReset.setBounds(10, 328, 89, 23);
        frmMachineLearning.getContentPane().add(btnReset);

        chckbxVerbose = new JCheckBox("Verbose");
        chckbxVerbose.setBounds(13, 137, 97, 23);
        frmMachineLearning.getContentPane().add(chckbxVerbose);

        outputPane = new JTextPane();
        scrollPane = new JScrollPane(outputPane);
        outputPane.setEditable(false);
        scrollPane.setBounds(143, 11, 281, 340);
        frmMachineLearning.getContentPane().add(scrollPane);

        lblNotSelected = new JLabel("Not selected");
        lblNotSelected.setEnabled(false);
        lblNotSelected.setBounds(10, 172, 100, 14);
        frmMachineLearning.getContentPane().add(lblNotSelected);

        lblNotSelected_1 = new JLabel("Not selected");
        lblNotSelected_1.setEnabled(false);
        lblNotSelected_1.setBounds(10, 233, 100, 14);
        frmMachineLearning.getContentPane().add(lblNotSelected_1);

        lblTestFile = new JLabel("File not selected");
        lblTestFile.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblTestFile.setBounds(10, 65, 100, 14);
        frmMachineLearning.getContentPane().add(lblTestFile);

        JButton button = new JButton("Select File");
        button.addActionListener(e -> {
            FileDialog fd = new FileDialog((FileDialog) null, "open file",
                    FileDialog.LOAD);

            fd.setVisible(true);
            String fileName = fd.getFile();
            String filePath = fd.getDirectory();
            if (fileName != null && filePath != null) {
                absolutePathTesting = filePath + fileName;
                lblTestFile.setText("File selected");
                lblTestFile.setEnabled(true);

            }
        });
        button.setBounds(10, 77, 100, 23);
        frmMachineLearning.getContentPane().add(button);
    }

    private void resetInputs() {
        lblFileNotSelected.setText("File not selected");
        chckbxNormalize.setSelected(false);
        chckbxVerbose.setSelected(false);
        outputPane.setText("");
        outputPane.setText("Reset performed");
        lblNotSelected.setEnabled(false);
        lblNotSelected.setText("Not selected");
        lblTestFile.setEnabled(false);
        lblTestFile.setText("Not selected");
        lblNotSelected_1.setEnabled(false);
        lblNotSelected_1.setText("Not selected");
        absolutePathTraining = "";
        selectedAlgorithm = "";
        selectedTrainingMethod = "";
        System.gc();
    }

    private void initializeForRun() {
        ml.setFilePath("/Users/andreirybin/Projects/cs-478/files/backprop.arff");//absolutePathTraining);
        //System.out.println(absolutePathTraining);
        ml.setLearnerName("backprop");//selectedAlgorithm);
        //System.out.println(absolutePathTraining);
        ml.setNormalize(chckbxNormalize.isSelected());
        ml.setVerbose(chckbxVerbose.isSelected());
        ml.setEvalMethod("training");//selectedTrainingMethod);
        ml.setTestFilePath("/Users/andreirybin/Projects/cs-478/files/backprop.arff");//absolutePathTesting);
        try {
            ml.resetStringBuilder();
            ml.run();
            outputPane.setText("");
            outputPane.setText(ml.getResults());
        } catch (Exception e1) {
            e1.printStackTrace();
            outputPane.setText(e1.toString());
        }
    }
}
