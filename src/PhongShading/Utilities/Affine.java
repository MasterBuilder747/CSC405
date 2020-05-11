package PhongShading.Utilities;

public class Affine extends Matrix {
	
	public static final int X = 0;
	public static final int Y = 1;
	public static final int Z = 2;
	
	public Affine (int axis, double theta)
	{
		super(4, 4);
		
		double m[][] = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
		double rads = theta * Math.PI / 180.0;
		
		switch (axis) {
		case X:
			m[1][1] =  Math.cos(rads);
			m[1][2] = -Math.sin(rads);
			m[2][1] =  Math.sin(rads);
			m[2][2] =  Math.cos(rads);
			break;
		case Y:
			m[0][0] =  Math.cos(rads);
			m[0][2] =  Math.sin(rads);
			m[2][0] = -Math.sin(rads);
			m[2][2] =  Math.cos(rads);
			break;
		case Z:
			m[0][0] =  Math.cos(rads);
			m[0][1] = -Math.sin(rads);
			m[1][0] =  Math.sin(rads);
			m[1][1] =  Math.cos(rads);
			break;
		default:
			m[1][1] =  Math.cos(rads);
			m[1][2] = -Math.sin(rads);
			m[2][1] =  Math.sin(rads);
			m[2][2] =  Math.cos(rads);
			break;				
		}
		
		M = m;
	}
	
	public Affine (double P0x, double P0y, double P0z, 
			double P1x, double P1y, double P1z,
			double angle)
	{
		super(4, 4);

		Matrix result;

		double arads = angle * Math.PI / 180.0;
		double theta;
		
		// -- translate rotation vector to origin
		double x = P1x - P0x;
		double y = P1y - P0y;
		double z = P1z - P0z;

		// -- normalize (unit vector) rotation vector
		double length = Math.sqrt(Math.pow(x, 2.0) + Math.pow(y, 2.0) + Math.pow(z, 2.0));
		double unitX = x / length;
		double unitY = y / length;
		double unitZ = z / length;

		double d = Math.sqrt(Math.pow(unitY, 2.0) + Math.pow(unitZ, 2.0));
		double Rx[][] = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}}; // -- rotate about X-axis
		double Ry[][] = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}}; // -- rotate about Y-axis
		double Rz[][] = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}}; // -- rotate about Z-axis
		double T[][]  = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};  // -- translate

		// -- Step 1: translate point to the origin
		T[0][0] = 1.0; T[0][1] = 0.0; T[0][2] = 0.0; T[0][3] = -P0x;
		T[1][0] = 0.0; T[1][1] = 1.0; T[1][2] = 0.0; T[1][3] = -P0y;
		T[2][0] = 0.0; T[2][1] = 0.0; T[2][2] = 1.0; T[2][3] = -P0z;
		T[3][0] = 0.0; T[3][1] = 0.0; T[3][2] = 0.0; T[3][3] = 1.0;

		// -- Step 2: positive rotation about the X axis (clockwise rotation)
		if (Math.abs(d) > 0.0001) { // -- if d is 0.0 use the identity matrix (no X-axis rotation required)
			theta = Math.acos(unitZ / d);
			Rx[0][0] = 1.0; Rx[0][1] = 0.0;             Rx[0][2] = 0.0;              Rx[0][3] = 0.0; 
			Rx[1][0] = 0.0; Rx[1][1] = Math.cos(theta); Rx[1][2] = -Math.sin(theta); Rx[1][3] = 0.0;
			Rx[2][0] = 0.0; Rx[2][1] = Math.sin(theta); Rx[2][2] = Math.cos(theta);  Rx[2][3] = 0.0;
			Rx[3][0] = 0.0; Rx[3][1] = 0.0;             Rx[3][2] = 0.0;              Rx[3][3] = 1.0;
			
		}
		/* -- The above conditional checks for the divide-by-zero case which
		      will cause problems (if d == 0.0) with the acos() call. Another
			  way around it is to use the atan2() function.
		      
		      MakeRotationXMatrix(atan2(unitY, unitZ), Rx);
		-- */
		result = Matrix.multiply(new Matrix(Rx), new Matrix(T));

		// -- Step 3: negative directional rotation about the Y axis (counter-clockwise rotation)
		theta = -Math.acos(d);
		Ry[0][0] = Math.cos(theta);  Ry[0][1] = 0.0; Ry[0][2] = Math.sin(theta); Ry[0][3] = 0.0; 
		Ry[1][0] = 0.0;              Ry[1][1] = 1.0; Ry[1][2] = 0.0;             Ry[1][3] = 0.0;
		Ry[2][0] = -Math.sin(theta); Ry[2][1] = 0.0; Ry[2][2] = Math.cos(theta); Ry[2][3] = 0.0;
		Ry[3][0] = 0.0;              Ry[3][1] = 0.0; Ry[3][2] = 0.0;             Ry[3][3] = 1.0; 
		result = Matrix.multiply(new Matrix(Ry), result);

		// -- Step 4: requested rotation about the Z axis (clockwise rotation)
		theta = arads;
		Rz[0][0] = Math.cos(theta);  Rz[0][1] = -Math.sin(theta); Rz[0][2] = 0.0; Rz[0][3] = 0.0; 
		Rz[1][0] = Math.sin(theta);  Rz[1][1] = Math.cos(theta);  Rz[1][2] = 0.0; Rz[1][3] = 0.0;
		Rz[2][0] = 0.0;              Rz[2][1] = 0.0;              Rz[2][2] = 1.0; Rz[2][3] = 0.0;
		Rz[3][0] = 0.0;              Rz[3][1] = 0.0;              Rz[3][2] = 0.0; Rz[3][3] = 1.0;
		result = Matrix.multiply(new Matrix(Rz), result);

		// -- Step 5: positive directional rotation about the Y axis (inverse of above) (clockwise rotation)
		theta = Math.acos(d);
		Ry[0][0] = Math.cos(theta);  Ry[0][1] = 0.0; Ry[0][2] = Math.sin(theta); Ry[0][3] = 0.0; 
		Ry[1][0] = 0.0;              Ry[1][1] = 1.0; Ry[1][2] = 0.0;             Ry[1][3] = 0.0;
		Ry[2][0] = -Math.sin(theta); Ry[2][1] = 0.0; Ry[2][2] = Math.cos(theta); Ry[2][3] = 0.0;
		Ry[3][0] = 0.0;              Ry[3][1] = 0.0; Ry[3][2] = 0.0;             Ry[3][3] = 1.0; 
		result = Matrix.multiply(new Matrix(Ry), result);

		// -- Step 6: negative directional rotation about the X axis (inverse of above) (counter-clockwise rotation)
		if (Math.abs(d) < 0.0001) { // -- if d is 0.0 use the identity matrix (no X-axis rotation required)
			Rx = new double[4][4];
			for (int i = 0; i < 4; ++i) {
				for (int j = 0; j < 4; ++j) {
					Rx[i][j] = 0.0;
				}
			}
			Rx[0][0] = Rx[1][1] = Rx[2][2] = Rx[3][3] = 1.0;
		}
		else {
			theta = -Math.acos(unitZ / d);
			Rx[0][0] = 1.0; Rx[0][1] = 0.0;             Rx[0][2] = 0.0;              Rx[0][3] = 0.0; 
			Rx[1][0] = 0.0; Rx[1][1] = Math.cos(theta); Rx[1][2] = -Math.sin(theta); Rx[1][3] = 0.0;
			Rx[2][0] = 0.0; Rx[2][1] = Math.sin(theta); Rx[2][2] = Math.cos(theta);  Rx[2][3] = 0.0;
			Rx[3][0] = 0.0; Rx[3][1] = 0.0;             Rx[3][2] = 0.0;              Rx[3][3] = 1.0;
		}
		/* -- The above conditional checks for the divide-by-zero case which
		      will cause problems (if d == 0.0) with the acos() call. Another
			  way around it is to use the atan2() function.
			  
			  MakeRotationXMatrix(-atan2(unitY, unitZ), Rx);
		-- */
		result = Matrix.multiply(new Matrix(Rx), result);

		// -- Step 7: translate point back to "original" position (inverse of above)
		T[0][0] = 1.0; T[0][1] = 0.0; T[0][2] = 0.0; T[0][3] = P0x;
		T[1][0] = 0.0; T[1][1] = 1.0; T[1][2] = 0.0; T[1][3] = P0y;
		T[2][0] = 0.0; T[2][1] = 0.0; T[2][2] = 1.0; T[2][3] = P0z;
		T[3][0] = 0.0; T[3][1] = 0.0; T[3][2] = 0.0; T[3][3] = 1.0;
		result = Matrix.multiply(new Matrix(T), result);

		this.M = result.M;
}


	
	public static void main (String args[])
	{
		Matrix R;
		
		Affine r0 = new Affine(Affine.X, -90); // theta x
		Affine r1 = new Affine(Affine.Y, -0); // theta y
		Affine r2 = new Affine(Affine.Z, 0);  // theta z
		Affine r3 = new Affine(Affine.Y, 0);  // theta y
		Affine r4 = new Affine(Affine.X, 90);  // theta x
		
		R = Matrix.multiply(r0,  r1);
		R = Matrix.multiply(R,  r2);
		R = Matrix.multiply(R,  r3);
		R = Matrix.multiply(R,  r4);
		
		System.out.println(R);
		
		double pt[][] = {{0}, {1}, {0}, {1}};
		Matrix P = new Matrix(pt);
		
		Affine af = new Affine (0, 0, 0,
							1, 2, 3,
							45.0);
		
		System.out.println(af);
		
		System.out.println(Matrix.multiply(af, P));

	}

}
