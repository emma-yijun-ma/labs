import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class App {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // create adjacency list representation of courses and prerequisites
        List<List<Integer>> g = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            g.add(new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites) {
            g.get(prerequisite[1]).add(prerequisite[0]);
        }

        // calculate the indegrees
        int[] indegree = new int[numCourses];
        for (List<Integer> dependencies : g) {
            for (int dependency : dependencies) {
                indegree[dependency]++;
            }
        }

        // use queue for bfs
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int count = 0;  
        while (!queue.isEmpty()) {
            int node = queue.poll();
            count++;

            // decrement indegree for each
            for (int dependency : g.get(node)) {
                indegree[dependency]--;
                if (indegree[dependency] == 0) {
                    queue.offer(dependency);
                }
            }
        }

        // if count == numCourses, then can be completed
        return count == numCourses;
    }
}
