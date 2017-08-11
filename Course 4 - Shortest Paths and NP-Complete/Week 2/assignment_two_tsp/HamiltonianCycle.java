package assignment_two_tsp;

/**
 * Bitmask dynamic programming solution that finds the shortest Hamiltonian cycle.
 * Also known as the traveling salesman problem.
 * 
 * Take time proportional to <em>2</em><sup>N</sup><em>N</em><sup>2</sup> where
 * <em>N</em> is the number of vertices.
 */
public class HamiltonianCycle {
    private double minDist;
    
    /**
     * Computes the shortest Hamiltonian cycle.
     * 
     * @param dist the 2D array of distances between vertices
     */
    public HamiltonianCycle(double[][] dist) {
        int n = dist.length;
        minDist = Double.POSITIVE_INFINITY;
        double[][] dp = new double[1 << n][n];
        for (int i = 0; i < 1 << n; i++)
            for (int j = 0; j < n; j++)
                dp[i][j] = Double.POSITIVE_INFINITY;

        dp[1][0] = 0;

        for (int i = 1; i < 1 << n; i += 2)
            for (int j = 0; j < n; j++)
                if ((i & 1 << j) != 0)
                    for (int k = 1; k < n; k++)
                        if ((i & 1 << k) == 0)
                            dp[i ^ 1 << k][k] = Math.min(dp[i ^ 1 << k][k], dp[i][j] + dist[j][k]);

        for (int i = 1; i < n; i++)
            minDist = Math.min(minDist, dp[(1 << n) - 1][i] + dist[i][0]);
    }
    
    /**
     * Returns the length of the shortest Hamiltonian Cycle.
     * 
     * @return the length of the shortest Hamiltonian Cycle
     */
    public double length() {
        return minDist;
    }
}
