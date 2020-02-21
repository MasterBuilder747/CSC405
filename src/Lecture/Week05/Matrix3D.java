package Lecture.Week05;

public class Matrix3D {

    //double[][] t = {{1, 0, 0, -x}, {0, 1, 0, -y}, {0, 0, 1, -z}, {0, 0, 0, x}};
    double[][] r = {{}, {}, {}, {0, 0, 0, 1}};
    //double[][] ti = {{, , , x}, {, , , y}, {, , , z}, {0, 0, 0, 1}};
    double[][] result;
    /*
    mat = matmult(r, t); //4x4
    mat = matmult(ti, mat); //4x4
    mat = matmult(mat, fb); //4x4
    */
    //order:
    //ti, r, t, fb
    /*
    result = matmult(t, fb);
    result = matmult(r, result);
    result = matmult(ti, result);
    return result;
    */
}
