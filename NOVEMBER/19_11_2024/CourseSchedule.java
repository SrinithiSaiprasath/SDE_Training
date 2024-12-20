import java.util.*;

public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }
        int[] inDegree = new int[numCourses];
        for (int[] prereq : prerequisites) {
            adjList.get(prereq[1]).add(prereq[0]);
            inDegree[prereq[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        int completedCourses = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            completedCourses++;
            for (int next : adjList.get(course)) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    queue.add(next);
                }
            }
        }
        return completedCourses == numCourses;
    }

    public static void main(String[] args) {
        CourseSchedule cs = new CourseSchedule();
        int[][] prerequisites = {{1, 0}, {0, 1}};
        System.out.println(cs.canFinish(2, prerequisites)); 
    }
}
