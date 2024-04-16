from collections import deque


def canFinish(self, numCourses, prerequisites):
        # create adjacency list representation of courses and prerequisites
        g = [[] for _ in range(numCourses)]
        for prerequisite in prerequisites:
            g[prerequisite[1]].append(prerequisite[0])

        # calculate the indegrees
        indegree = [0] * numCourses
        for dependencies in g:
            for dependency in dependencies:
                indegree[dependency] += 1

        # use queue for bfs
        queue = deque()
        for i in range(numCourses):
            if indegree[i] == 0:
                queue.append(i)

        count = 0  
        while queue:
            node = queue.popleft()
            count += 1

            # decrement indegree for each
            for dependency in g[node]:
                indegree[dependency] -= 1
                if indegree[dependency] == 0:
                    queue.append(dependency)

        # if count == numCourses, then can be completed
        return count == numCourses