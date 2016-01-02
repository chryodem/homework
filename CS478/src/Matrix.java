// ----------------------------------------------------------------
// The contents of this file are distributed under the CC0 license.
// See http://creativecommons.org/publicdomain/zero/1.0/
// ----------------------------------------------------------------

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Matrix {
    //Andrei added these stuff
    private StringBuilder sb;
    private boolean threwException = false;
    // Data
    private List<double[]> m_data;
    private double[] m_column_mean;
    private double[] m_column_min;
    private double[] m_column_max;

    // Meta-data
    private List<String> m_attr_name;
    private List<TreeMap<String, Integer>> m_str_to_enum;
    private List<TreeMap<Integer, String>> m_enum_to_str;

    private final static double MISSING = Double.MIN_VALUE; // representation of
    // missing values in the
    // dataset
    private final static Set<String> ATTRIBUTES_TO_IGNORE = Stream.of("REAL", "CONTINUOUS", "INTEGER").
            collect(Collectors.toCollection(HashSet::new));

    public Matrix() {
        initializeMetaStorages();
    }


    // Copies the specified portion of that matrix into this matrix
    public Matrix(Matrix that, int rowStart, int colStart, int rowCount,
                  int colCount) {
        m_data = new ArrayList<>();
        for (int j = 0; j < rowCount; j++) {
            double[] rowSrc = that.row(rowStart + j);
            double[] rowDest = new double[colCount];
            for (int i = 0; i < colCount; i++)
                rowDest[i] = rowSrc[colStart + i];
            m_data.add(rowDest);
        }
        initializeMetaStorages();
        for (int i = 0; i < colCount; i++) {
            m_attr_name.add(that.attrName(colStart + i));
            m_str_to_enum.add(that.m_str_to_enum.get(colStart + i));
            m_enum_to_str.add(that.m_enum_to_str.get(colStart + i));
        }
    }

    private void initializeMetaStorages() {
        m_attr_name = new ArrayList<>();
        m_str_to_enum = new ArrayList<>();
        m_enum_to_str = new ArrayList<>();
    }
    //storages for min,max,mean
    private void initilizeDataStorages() {
        m_column_mean = new double[cols()];
        m_column_max = new double[cols()];
        m_column_min = new double[cols()];
        Arrays.parallelSetAll(m_column_mean, i -> MISSING);
        Arrays.parallelSetAll(m_column_max, i -> MISSING);
        Arrays.parallelSetAll(m_column_min, i -> MISSING);

    }

    // Adds a copy of the specified portion of that matrix to this matrix
    public void add(Matrix that, int rowStart, int colStart, int rowCount)
            throws Exception {
        if (colStart + cols() > that.cols())
            throw new Exception("out of range");
        for (int i = 0; i < cols(); i++) {
            if (that.valueCount(colStart + i) != valueCount(i))
                throw new Exception("incompatible relations");
        }
        for (int j = 0; j < rowCount; j++) {
            double[] rowSrc = that.row(rowStart + j);
            double[] rowDest = new double[cols()];
            for (int i = 0; i < cols(); i++)
                rowDest[i] = rowSrc[colStart + i];
            m_data.add(rowDest);
        }
    }

    // Resizes this matrix (and sets all attributes to be continuous)
    public void setSize(int rows, int cols) {
        m_data = new ArrayList<>();
        for (int j = 0; j < rows; j++) {
            double[] row = new double[cols];
            m_data.add(row);
        }
        m_attr_name = new ArrayList<>();
        m_str_to_enum = new ArrayList<>();
        m_enum_to_str = new ArrayList<>();
        for (int i = 0; i < cols; i++) {
            m_attr_name.add("");
            m_str_to_enum.add(new TreeMap<>());
            m_enum_to_str.add(new TreeMap<>());
        }
    }

    // Loads from an ARFF file
    public void loadArff(String filename) {
        threwException = false;
        sb = new StringBuilder();

        m_data = new ArrayList<>();
        m_attr_name = new ArrayList<>();
        m_str_to_enum = new ArrayList<>();
        m_enum_to_str = new ArrayList<>();
        boolean READDATA = false;

        try (Stream<String> stream = Files.lines(Paths.get(filename))
                .parallel()
                .filter(s -> s.length() > 0 && s.charAt(0) != '%') //skip comments
        ) {
            Iterator<String> iter = stream.iterator();
            while (iter.hasNext()) {
                String input = iter.next();
                StringTokenizer tokenizer = new StringTokenizer(input," \t\n" +
                        "\n" +
                        "\f" + ",{}");
                if (!READDATA) {
                    String preDataTokens = tokenizer.nextToken().toUpperCase();
                    switch (preDataTokens) {
                        //this is the name of the data set, don't care about it at the moment
                        case "@RELATION":
                            break;
                        case "@ATTRIBUTE":
                            TreeMap<String, Integer> ste = new TreeMap<>();
                            m_str_to_enum.add(ste);
                            TreeMap<Integer, String> ets = new TreeMap<>();
                            m_enum_to_str.add(ets);

                            String attributeName = tokenizer.nextToken();
                            m_attr_name.add(attributeName);
                            String types = tokenizer.nextToken();
                            //if it's anything other than CONTINUOUS input
                            //next token is either REAL or {
                            if (!ATTRIBUTES_TO_IGNORE.contains(types.toUpperCase())) {
                                int vals = 0;
                                ste.put(types, vals);
                                ets.put(vals, types);
                                vals++;
                                while (tokenizer.hasMoreTokens()) {
                                    types = tokenizer.nextToken();
                                    ste.put(types, vals);
                                    ets.put(vals, types);
                                    vals++;
                                }
                            }
                            break;
                        case "@DATA":
                            READDATA = true;
                            break;
                    }
                } else { //it's data we are reading
                    double[] newrow = new double[cols()];
                    int curPosition = 0;
                    while(tokenizer.hasMoreTokens()) {
                        int vals = m_enum_to_str.get(curPosition).size();
                        String stringValue = tokenizer.nextToken();
                        double value = 0;
                        if(stringValue.equals("?")) {
                            value = MISSING;
                        } else if (vals == 0) {
                            value = Double.parseDouble(stringValue);
                        } else if(Character.isAlphabetic(stringValue.charAt(0))){

                        } else { //the values are discrete
                            value = m_str_to_enum.get(curPosition).get(stringValue);
                        }
                        newrow[curPosition] = value;
                        curPosition++;
                    }
                    m_data.add(newrow);
                }
            }
        } catch (IOException e) {
            threwException = true;
            sb.append("File operation failed " + e.toString());
        }
        initilizeDataStorages();
    }

    public boolean errorsReading() {
        return threwException;
    }

    public StringBuilder errorMessage() {
        return sb;
    }

    // Returns the number of rows in the matrix
    public int rows() {
        return m_data.size();
    }

    // Returns the number of columns (or attributes) in the matrix
    public int cols() {
        return m_attr_name.size();
    }

    // Returns the specified row
    public double[] row(int r) {
        return m_data.get(r);
    }

    // Returns the element at the specified row and column
    public double get(int r, int c) {
        return m_data.get(r)[c];
    }

    // Sets the value at the specified row and column
    public void set(int r, int c, double v) {
        row(r)[c] = v;
    }

    // Returns the name of the specified attribute
    public String attrName(int col) {
        return m_attr_name.get(col);
    }

    // Set the name of the specified attribute
    public void setAttrName(int col, String name) {
        m_attr_name.set(col, name);
    }

    // Returns the name of the specified value
    public String attrValue(int attr, int val) {
        return m_enum_to_str.get(attr).get(val);
    }

    // Returns the number of values associated with the specified attribute (or
    // column)
    // 0=continuous, 2=binary, 3=trinary, etc.
    public int valueCount(int col) {
        return m_enum_to_str.get(col).size();
    }

    // Shuffles the row order
    public void shuffle(Random rand) {
        for (int n = rows(); n > 0; n--) {
            int i = rand.nextInt(n);
            double[] tmp = row(n - 1);
            m_data.set(n - 1, row(i));
            m_data.set(i, tmp);
        }
    }

    // Returns the mean of the specified column
    public double columnMean(int col) {
        double columnMean = m_column_mean[col];
        if(columnMean == MISSING) {
            double sum = 0;
            int count = 0;
            for (int i = 0; i < rows(); i++) {
                double v = get(i, col);
                if (v != MISSING) {
                    sum += v;
                    count++;
                }
            }
            columnMean = sum / count;
            m_column_mean[col] = columnMean;
        }
        return columnMean;
    }

    // Returns the min value in the specified column
    public double columnMin(int col) {
        double m = m_column_min[col];
        if(m == MISSING) {
            for (int i = 0; i < rows(); i++) {
                double v = get(i, col);
                if (v != MISSING) {
                    if (m == MISSING || v < m)
                        m = v;
                }
            }
            m_column_min[col] = m;
        }
        return m;
    }

    // Returns the max value in the specified column
    public double columnMax(int col) {
        double m = m_column_max[col];
        if(m == MISSING) {
            for (int i = 0; i < rows(); i++) {
                double v = get(i, col);
                if (v != MISSING) {
                    if (m == MISSING || v > m)
                        m = v;
                }
            }
            m_column_max[col] = m;
        }
        return m;
    }

    // Returns the most common value in the specified column
    public double mostCommonValue(int col) {
        TreeMap<Double, Integer> tm = new TreeMap<>();
        for (int i = 0; i < rows(); i++) {
            double v = get(i, col);
            if (v != MISSING) {
                Integer count = tm.get(v);
                if (count == null)
                    tm.put(v, 1);
                else
                    tm.put(v, count + 1);
            }
        }
        return Collections.max(tm.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public void normalize() {
        for (int i = 0; i < cols(); i++) {
            if (valueCount(i) == 0) {
                double min = columnMin(i);
                double max = columnMax(i);
                for (int j = 0; j < rows(); j++) {
                    double v = get(j, i);
                    if (v != MISSING) {
                        set(j, i, (v - min) / (max - min));
                    } else { //if it's missing replace with the mean value
                        set(j, i, columnMean(i));
                    }
                }
            }
        }
    }

    public StringBuilder print() {
        sb = new StringBuilder();
        sb.append("@RELATION Untitled\n");
        for (int i = 0; i < m_attr_name.size(); i++) {
            sb.append("@ATTRIBUTE " + m_attr_name.get(i));
            int vals = valueCount(i);
            if (vals == 0) {
                sb.append("\n CONTINUOUS");

            } else {
                sb.append("{");
                for (int j = 0; j < vals; j++) {
                    if (j > 0) {
                        sb.append(", ");
                    }
                    sb.append((m_enum_to_str.get(i).get(j)));
                }
                sb.append("}\n");
            }
        }
        sb.append("\n@DATA\n");
        for (int i = 0; i < rows(); i++) {
            double[] r = row(i);
            for (int j = 0; j < r.length; j++) {
                if (j > 0) {
                    sb.append(", ");
                }
                if (valueCount(j) == 0) {
                    sb.append(r[j]);

                } else {
                    sb.append((m_enum_to_str.get(j).get((int) r[j])));
                }
            }
            sb.append(" \n");
        }
        return sb;
    }
}
