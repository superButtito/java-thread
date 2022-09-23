import java.util.Arrays;
//64050676 วีรภัทร พรหมวิจิต
public class Lab_MatrixMul {
    public static void main(String[] args) {
        int[][] inputA = { { 5, 6, 7 }, { 4, 8, 9 } };
        int[][] inputB = { { 6, 4 }, { 5, 7 }, { 1, 1 } };
        MyData matA = new MyData(inputA);
        MyData matB = new MyData(inputB);
        int matC_r = matA.data.length;
        int matC_c = matB.data[0].length;
        MyData matC = new MyData(matC_r, matC_c);

        //q4
        Thread p00 = new Thread(new MatrixMulThread(0, 0, matA, matB, matC));
        p00.start();
        Thread p01 = new Thread(new MatrixMulThread(0, 1, matA, matB, matC));
        p01.start();
        Thread p10 = new Thread(new MatrixMulThread(1, 0, matA, matB, matC));
        p10.start();
        Thread p11 = new Thread(new MatrixMulThread(1, 1, matA, matB, matC));
        p11.start();
        //q5
        try {
            p00.join();
            p01.join();
            p10.join();
            p11.join();
        }
        catch (Exception e) { }



        matC.show();
    }
}
class MatrixMulThread implements Runnable {
    int processing_row;
    int processing_col;
    MyData datA;
    MyData datB;
    MyData datC;

    MatrixMulThread(int tRow, int tCol, MyData a, MyData b, MyData c) {
        //q1
        processing_row = tRow;
        processing_col = tCol;
        datA = a;
        datB = b;
        datC = c;
    }
    //q2
    public void run() {
        //q3
        int tmp = 0;
        for(int i = 0; i < 3; i++) {
            tmp += datA.data[processing_col][i] * datB.data[i][processing_row];
        }
        datC.data[processing_col][processing_row] = tmp;
        System.out.println("perform sum ofmultiplication of assigned row and col" + " " + datC.data[processing_col][processing_row]);
    }
}
class MyData {
    int[][] data;
    MyData(int[][] m) { data = m; }
    MyData(int r, int c) {
        data = new int[r][c];
        for (int[] aRow : data) {
            Arrays.fill(aRow, 9);
        }
    }
    void show() {
        System.out.println(Arrays.deepToString(data));
    }
}
